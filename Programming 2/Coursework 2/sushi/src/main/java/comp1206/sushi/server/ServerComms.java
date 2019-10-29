package comp1206.sushi.server;
import comp1206.sushi.common.*;

import java.io.*;
import java.util.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerComms implements Runnable {

    private Socket socket;
    private Server server;
    private ServerSocket serverSocket;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private ArrayList<Thread> commsThreads = new ArrayList<Thread>();

    public ServerComms(Server server) {
        this.server = server;
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
        } catch (IOException e) {}
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted");
                output = new ObjectOutputStream(socket.getOutputStream());
                Thread newOutputThread = new Thread(new ServerCommsOutputThread(socket, server, output,input));
                commsThreads.add(newOutputThread);
                newOutputThread.start();
            } catch (IOException e) {}
        }
    }
}
