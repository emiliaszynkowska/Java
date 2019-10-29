package comp1206.sushi.client;

import comp1206.sushi.common.*;
import java.io.*;
import java.util.*;
import java.net.Socket;

public class ClientComms implements Runnable {

    private Socket socket;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private Restaurant restaurant;
    private ArrayList<Postcode> postcodes = new ArrayList<Postcode>();
    private ArrayList<Dish> dishes = new ArrayList<Dish>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<User> users = new ArrayList<User>();
    private Client client;

    public ClientComms(Client client) {
        this.client = client;
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Connected");
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            if(e instanceof java.net.ConnectException) {
                System.out.println("Could not connect to a server. Closing...");
                System.exit(0);
            }
        }
    }

    public synchronized void addUser(User user) {
        users.add(user);
        try {
            output.writeUnshared(user);
            output.flush();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
    public synchronized void addOrder(Order order, Map<Dish, Number> contents) {
        orders.add(order);
        try {
            output.writeUnshared(order);
            output.flush();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
    public synchronized void cancelOrder(Order order) {
        try {
            output.writeUnshared(order);
            output.flush();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void run() {
        try {
            this.input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException i) {
            System.out.println(i);
            i.printStackTrace();
        }
        while (true) {
            boolean duplicate = false;
            try {
                Object current = input.readUnshared();
                if (current instanceof Restaurant) {
                    restaurant = (Restaurant) current;
                }
                else if (current instanceof Postcode) {
                    Postcode c = (Postcode) current;
                    for(Postcode p : postcodes) {
                        if(p.getName().equals(c.getName())) {
                            duplicate = true;
                            break;
                        }
                    }
                    if(duplicate == false)
                        postcodes.add(c);
                    duplicate = false;
                }
                else if (current instanceof Dish) {
                    Dish c = (Dish) current;
                    for(Dish d : dishes) {
                        if(d.getName().equals(c.getName())) {
                            duplicate = true;
                            break;
                        }
                    }
                    if(duplicate == false)
                        dishes.add((Dish)current);
                    duplicate = false;
                }
                else if (current instanceof User) {
                    User c = (User) current;
                    for (User u : users) {
                        if (u.getName().equals(c.getName())) {
                            duplicate = true;
                            break;
                        }
                    }
                    if(duplicate == false)
                        users.add(c);
                    duplicate = false;
                }
                else if (current instanceof Order) {
                    if(client.getUser() != null) {
                        Order c = (Order)current;
                        if (c.user.getName().equals(client.getUser().getName())) {
                            for (Order o : orders) {
                                if (o.getName().equals(c.getName())) {
                                    duplicate = true;
                                    orders.remove(o);
                                    orders.add(c);
                                    break;
                                }
                            }
                            if (duplicate == false)
                                orders.add(c);
                            duplicate = false;
                        }
                    }
                }
                input.reset();
                Thread.sleep(5000);
            } catch (StreamCorruptedException s) {
                System.out.println(s);
            } catch (IOException e) {
                if(e instanceof java.net.SocketException) {
                    System.out.println("Server not detected. Closing...");
                    System.exit(0);
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e);
                continue;
            } catch (NullPointerException n) {
                System.out.println(n);
                n.printStackTrace();
                continue;
            } catch (ConcurrentModificationException c) {
                continue;
            } catch (InterruptedException i) {}
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public String getRestaurantName() {
        return restaurant.getName();
    }
    public Postcode getRestaurantPostcode() {
        return restaurant.getLocation();
    }
    public ArrayList<Postcode> getPostcodes() {
        return postcodes;
    }
    public ArrayList<Dish> getDishes() {
        return dishes;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }

}
