import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

public class TCPSender implements Runnable {
    private Participant parent;
    private ArrayList<Integer> ports;
    private HashMap<String,Boolean> votesToSend;
    private Socket sendSocket;
    PrintWriter out;
    BufferedReader in;

    /*
    Create a new TCPSender
    TCPSender is used by Participants to exchange votes
    Implements Runnable and can be run concurrently with TCPReceiver
     */
    public TCPSender(Participant parent, ArrayList<Integer> ports) {
        this.parent = parent;
        this.ports = ports;
    }

    @Override
    public synchronized void run() {
        while(true) {
            while(true) {
                try {
                    // Update the HashMap of votesToSend
                    this.votesToSend = (HashMap<String,Boolean>) parent.getVotesToSend().clone();
                    break;
                } catch (ConcurrentModificationException c) {}
            }
            // Send a vote to the other participants
            for (Integer port : ports) {
                for (String vote : votesToSend.keySet()) {
                    if (votesToSend.get(vote) == false) {
                        Boolean sent = false;
                        while (!sent) {
                            try {
                                ServerSocket sendServerSocket = new ServerSocket(port);
                                sendSocket = sendServerSocket.accept();
                                out = new PrintWriter(sendSocket.getOutputStream(), true);
                                // Send as a String
                                out.println(vote);
                                // Send as a Vote object
                                out.println("VOTE_OBJECT " + port + " " + vote);
                                out.close();
                                sendSocket.close();
                                votesToSend.replace(vote,true);
                                if (parent.getVotesSent().containsKey(port)) {
                                    parent.getVotesSent().get(port).add(new Vote(port, vote));
                                } else {
                                    ArrayList<Vote> votes = new ArrayList<>();
                                    votes.add(new Vote(port,vote));
                                    parent.getVotesSent().put(port,votes);
                                }
                                ParticipantLogger.getLogger().messageSent(port,vote);
                                sent = true;
                            } catch (IOException e) {}
                        }
                    }
                }
            }
        }
    }
}