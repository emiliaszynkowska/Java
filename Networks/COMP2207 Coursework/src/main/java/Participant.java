import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class Participant {

    private int cport;
    private int lport;
    private int pport;
    private int timeout;
    private ArrayList<Integer> participants;
    private ArrayList<String> options;
    private HashMap<Integer,String> votes;
    private ArrayList<String> numberOfVotesReceived;
    private Map<Integer,ArrayList<Vote>> votesReceived;
    private Map<Integer,ArrayList<Vote>> votesSent;
    private HashMap<String,Boolean> votesToSend;
    private Socket clientSocket;
    PrintWriter out;
    BufferedReader in;

    public HashMap<Integer, String> getVotes() {
        return votes;
    }
    public synchronized ArrayList<String> getNumberOfVotesReceived() { return numberOfVotesReceived; }
    public synchronized Map<Integer,ArrayList<Vote>> getVotesReceived() { return votesReceived; }
    public synchronized Map<Integer,ArrayList<Vote>> getVotesSent() { return votesSent; }
    public synchronized HashMap<String,Boolean> getVotesToSend() { return votesToSend; }

    /*
    Get arguments <cport> <lport> <pport> <timeout>
    Create a new Participant
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        new Participant(Integer.valueOf(args[0]),Integer.valueOf(args[1]),Integer.valueOf(args[2]),Integer.valueOf(args[3]));
    }

    /*
    Create a new participant using the given arguments
    Initialise global variables
    Initialise the ParticipantLogger class
     */
    public Participant(int cport, int lport, int pport, int timeout) throws IOException, InterruptedException {
        this.cport = cport;
        this.lport = lport;
        this.pport = pport;
        this.timeout= timeout;
        participants = new ArrayList<>();
        options = new ArrayList<>();
        votes = new HashMap<>();
        numberOfVotesReceived = new ArrayList<>();
        votesReceived = new HashMap<>();
        votesSent = new HashMap<>();
        votesToSend = new HashMap<>();
        long longTime = System.currentTimeMillis();
        int intTime = (int) longTime;
        ParticipantLogger.initLogger(lport,intTime,timeout);
        run();
    }

    /*
    Method encapsulating all main functions
    Send a join message of the form JOIN <port>
    Get details from the coordinator
    Get options from the coordinator
    Vote with other participants
    Send the outcome to the coordinator
     */
    public void run() throws InterruptedException {
        Boolean success = false;
        while(!success) {
            try {
                Thread.sleep(1000);
                newSocket(cport);
                out = new PrintWriter(clientSocket.getOutputStream());
                String message = "JOIN " + pport;
                out.println(message);
                ParticipantLogger.getLogger().joinSent(cport);
                out.close();
                clientSocket.close();
                success = true;
            } catch (IOException e) {
                System.out.println("Connection failed. Retrying...");
            }
        }
            Thread.sleep(timeout);
            getDetails();
            getOptions();
            vote();
            outcome();
        }

    /*
    Get details of other participants as DETAILS [<port>]
     */
    private void getDetails() throws InterruptedException {
        Boolean received = false;
        while (!received) {
            try {
                newSocket(pport);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ParticipantLogger.getLogger().startedListening();
                String message = in.readLine();
                if (message != null && message.contains("DETAILS")) {
                    String[] messageSplit = message.split(" ");
                    for (int i=1; i<messageSplit.length; i++) {
                        participants.add(Integer.valueOf(messageSplit[i]));
                    }
                    received = true;
                }
                in.close();
                clientSocket.close();
            } catch (IOException e) {}
        }
        ParticipantLogger.getLogger().detailsReceived(participants);
        Thread.sleep(1000);
    }

    /*
    Get vote options from the coordinator as VOTE_OPTIONS [<option>]
     */
    public void getOptions() throws InterruptedException {
        Boolean received = false;
        while (!received) {
            try {
                newSocket(pport);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ParticipantLogger.getLogger().startedListening();
                String message = in.readLine();
                if (message != null && message.contains("VOTE_OPTIONS")) {
                    String[] messageSplit = message.split(" ");
                    for (int i=1; i<messageSplit.length; i++) {
                        options.add(messageSplit[i].trim());
                    }
                    received = true;
                }
                in.close();
                clientSocket.close();
            } catch (IOException e) {}
        }
        ParticipantLogger.getLogger().voteOptionsReceived(options);
        Thread.sleep(1000);
    }

    /*
    Decide on a vote from the options received
     */
    public String decideInitialVote() {
        Random random = new Random();
        String vote = options.get(random.nextInt(options.size()-1));
        votes.put(pport,vote);
        return vote;
    }

    /*
    Send votes to other participants as the byte stream VOTE <port> <vote>
     */
    public void vote() throws InterruptedException {
        String vote = decideInitialVote();

        // Decide which votes need to be sent
        votesToSend.put(("VOTE " + pport + " " + vote),false);

        ArrayList<Integer> ports = new ArrayList<>();
        for (Integer participant : participants) {
            if (participant != pport)
                ports.add(participant);
        }

        // ReceiveThread constantly receives messages via TCP
        Thread receiveThread = new Thread(new TCPReceiver(this, pport));
        receiveThread.start();
        // SendThread sends votes via TCP
        Thread sendThread = new Thread(new TCPSender(this, ports));
        sendThread.start();

        // Start the next round
        int round = participants.size();
        for (int i=0; i<=round; i++) {
            ParticipantLogger.getLogger().beginRound(i+1);

            // Add unsent votes to votesToSend
            while(true) {
                try {
                    for (String receivedVote : numberOfVotesReceived) {
                        if (!(votesToSend.containsKey(receivedVote))) {
                            votesToSend.put(receivedVote,false);
                        }
                    }
                    break;
                } catch (ConcurrentModificationException c) {}
            }

            // Clear votes for the next round
            numberOfVotesReceived.clear();
            // Reset counter for the next round
            int counter = 0;

            // Receive votes until the correct number of votes have been received
            while (numberOfVotesReceived.size() < participants.size()-1) {
                Thread.sleep(3000);
                counter += 3;
                if(counter > 10) {
                    break;
                }
            }

            ParticipantLogger.getLogger().endRound(i+1);
        }
        receiveThread.stop();
        sendThread.stop();

        for (Map.Entry<Integer,ArrayList<Vote>> i : votesReceived.entrySet()) {
            ParticipantLogger.getLogger().votesReceived(i.getKey(),i.getValue());
        }
        for (Map.Entry<Integer,ArrayList<Vote>> j : votesSent.entrySet()) {
            ParticipantLogger.getLogger().votesSent(j.getKey(),j.getValue());
        }
    }

    /*
    Send the outcome to the coordinator as the byte stream OUTCOME <outcome> [<port>]
     */
    public void outcome() {
        // Select the majority vote
        HashMap<String,Integer> outcomes = new HashMap<>();
        for (Map.Entry entry : votes.entrySet()) {
            if (!(outcomes.containsValue(entry.getValue()))) {
                outcomes.put((String) entry.getValue(), 1);
            } else {
                for (String key : outcomes.keySet()) {
                    if (key.equals(entry.getValue())) {
                        outcomes.replace(key,outcomes.get(key)+1);
                    }
                }
            }
        }
        String outcome = Collections.max(outcomes.entrySet(), Map.Entry.comparingByValue()).getKey();
        ParticipantLogger.getLogger().outcomeDecided(outcome);

        // Decide which participant is the sender of this vote
        String participantsString = "";
        for (int participant : participants) {
            participantsString += participant;
            participantsString += " ";
        }

        // Send the outcome to the coordinator
        String output = "OUTCOME " + outcome + " " + participantsString;
        try {
            newSocket(cport);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.write(output, 0, output.length());
            ParticipantLogger.getLogger().outcomeNotified(outcome);
            out.close();
            clientSocket.close();
        } catch (IOException e) {}
    }

    public void newSocket(int port) {
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), port);
            ParticipantLogger.getLogger().connectionEstablished(port);
        } catch (IOException e) {}
    }

}
