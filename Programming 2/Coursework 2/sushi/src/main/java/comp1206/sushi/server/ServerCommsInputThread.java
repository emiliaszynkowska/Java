package comp1206.sushi.server;

import comp1206.sushi.common.*;
import java.net.Socket;
import java.io.*;
import java.net.SocketException;
import java.util.*;

public class ServerCommsInputThread implements Runnable {

    private Server server;
    private Socket socket;
    private static ObjectInputStream input;

    public ServerCommsInputThread(Socket socket, Server server, ObjectInputStream input) {
        this.server = server;
        this.socket = socket;
        this.input = input;
    }

    public void run() {
        try {
            this.input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException i) {
            System.out.println(i);
        }
        while(true) {
            try {
                Object current = input.readUnshared();
                if (current instanceof User) {
                    server.addUser((User) current);
                }
                else if (current instanceof Order) {
                    Order c = (Order) current;
                    for (Order o : server.getOrders()) {
                        if (o.getName().equals(c.getName())) {
                            server.removeOrder(o);
                            break;
                        }
                    }
                    server.addOrder(c);
                }
                server.notifyUpdate();
            } catch (SocketException s) {
                break;
            } catch (IOException i) {
                System.out.println(i);
                continue;
            } catch (ClassNotFoundException c) {
                System.out.println(c);
                continue;
            } catch (NullPointerException n) { continue;
            } catch (ConcurrentModificationException c) { continue;
            }
        }
    }

}
