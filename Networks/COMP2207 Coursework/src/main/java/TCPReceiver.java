import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class TCPReceiver implements Runnable {
    private Participant parent;
    private Integer port;
    private Socket receiveSocket;
    PrintWriter out;
    BufferedReader in;

    /*
    Create a new TCPReceiver
    TCPReceiver is used by Participants to exchange votes
    Implements Runnable and can be run concurrently with TCPSender
     */
    public TCPReceiver(Participant parent, Integer port) {
        this.parent = parent;
        this.port = port;
    }

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        while(true) {
            try {
                // Receive a vote
                // Votes can be received as a String or a Vote object
                // This is because Participants take String objects, and the ParticipantLogger takes Vote objects
                receiveSocket = new Socket(InetAddress.getLocalHost(), port);
                in = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
                String message = in.readLine().trim();

                // Receive a vote as a string
                // Add the vote to the votes HashMap if not added already
                if (message != null && message.contains("VOTE") && !(parent.getNumberOfVotesReceived().contains(message))) {
                String[] messageSplit = message.split(" ");
                    for (int i=1; i<messageSplit.length; i+=2) {
                        while(true) {
                            try {
                                parent.getVotes().put(Integer.valueOf(messageSplit[i]), messageSplit[i+1]);
                                break;
                            } catch (ConcurrentModificationException c) {}
                        }
                    }
                    parent.getNumberOfVotesReceived().add(message);
                }

                // Receive a vote as a Vote object
                // Add the vote to the votesReceived HashMap
                else if (message != null && message.contains("VOTE_OBJECT")) {
                    String[] messageSplit = message.split(" ");
                    Integer sender = Integer.parseInt(messageSplit[1]);
                    String vote = "";
                    for (int i=2; i<messageSplit.length; i++) {
                        vote += messageSplit[i];
                        vote += " ";
                    }
                    if (parent.getVotesReceived().containsKey(sender)) {
                        parent.getVotesReceived().get(sender).add(new Vote(sender, vote.trim()));
                    } else {
                        ArrayList<Vote> votes = new ArrayList<>();
                        votes.add(new Vote(port,vote));
                        parent.getVotesReceived().put(sender,votes);
                    }
                    ParticipantLogger.getLogger().messageReceived(sender, vote);
                }
                in.close();
                receiveSocket.close();
            } catch (IOException e) {}
        }
    }
}