package comp1206.sushi.server;

import comp1206.sushi.server.Server;
import comp1206.sushi.common.*;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ConcurrentModificationException;

public class ServerCommsOutputThread implements Runnable {

    private Server server;
    private Socket socket;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static File file;
    private static ObjectOutputStream data;

    public ServerCommsOutputThread(Socket socket, Server server, ObjectOutputStream output, ObjectInputStream input) {
        this.socket = socket;
        this.server = server;
        this.output = output;
        this.input = input;
        Thread newInputThread = new Thread(new ServerCommsInputThread(socket, server, input));
        newInputThread.start();
    }

    public void run() {
        while(true) {
            try {
                output.writeUnshared(server.getRestaurant());
                output.flush();
                for (User u : server.getUsers()) {
                    output.writeUnshared(u);
                    output.flush();
                }
                for (Postcode p : server.getPostcodes()) {
                    output.writeUnshared(p);
                    output.flush();
                }
                for (Dish d : server.getDishes()) {
                    output.writeUnshared(d);
                    output.flush();
                }
                for (Order o : server.getOrders()) {
                    output.writeUnshared(o);
                    output.flush();
                }
                Thread.sleep(3000);
            } catch (SocketException s) {
                System.out.println("Client disconnected");
                break;
            } catch (IOException e) {
                System.out.println("Client disconnected");
                break;
            } catch (ConcurrentModificationException c) {
                continue;
            } catch (InterruptedException i) {}
        }
    }

}
