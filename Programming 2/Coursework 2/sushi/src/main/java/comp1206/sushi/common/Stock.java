package comp1206.sushi.common;
import comp1206.sushi.server.*;

import java.util.Map;

public class Stock {

    private static Server server;

    public void setServer(Server server) {
        this.server = server;
    }
    public Server getServer() { return this.server; }

    public void checkDishes(Staff staff) {
        for (Dish d : server.getDishes()) {
            check(d, staff);
        }
    }
    public void checkIngredients(Drone drone) {
        drone.setDestination(server.getRestaurantPostcode());
        for(Ingredient i : server.getIngredients()) {
            check(i, drone);
        }
    }

    public void check(Dish current, Staff staff) {
        if((Integer) current.getStock() < (Integer) current.getRestockThreshold()) {
            staff.setStatus("Working");
            restockDish(current);
            staff.checkBattery();
            staff.setStatus("Idle");
        }
        else { return; }
    }
    public void check(Ingredient current, Drone drone) {
        if((int)current.getStock() < (int)current.getRestockThreshold()) {
            drone.setStatus("Working");
            restockIngredient(current);
            drone.setDestination(null);
            drone.checkBattery();
            drone.setStatus("Idle");
        } else { return; }

    }
    public void checkOrders(Drone drone) {
        for(Order order : server.getOrders()) {
            if ((order.isDelivering() == false) && (order.getStatus().equals("Recieved"))) {
                order.setDelivering();
                deliver(drone,order);
                server.notifyUpdate();
            }
        }
    }

    public synchronized void restockDish(Dish current) {
        current.setStock(current, (Integer) current.getStock() + (Integer) current.getRestockAmount());
    }
    public synchronized void restockIngredient(Ingredient current) {
        current.setStock(current, (Integer) current.getStock() + (Integer) current.getRestockAmount());
    }
    public synchronized void deliver(Drone drone, Order order) {
        order.setStatus("Delivering");
        Map<Dish,Number> contents = order.getContents();
        for(Object dish : (contents.keySet())) {
            Integer number = ((Integer) contents.get(dish));
            for (Dish d : server.getDishes()) {
                if (d.getName().equals(((Dish) dish).getName())) {
                    if(((Integer)d.getStock() - number) > 0) {
                        d.setStock(d, (Integer) d.getStock() - number);
                        Map recipe = d.getRecipe();
                        for (Object ingredient : recipe.keySet()) {
                            for (Ingredient i : server.getIngredients()) {
                                if (i.getName().equals(((Ingredient) ingredient).getName())) {
                                    i.setStock(i, (Integer) i.getStock() - (number * (Integer) recipe.get(ingredient)));
                                }
                            }
                        }
                    }
                    else {
                        try {
                            drone.setStatus("Waiting for restock");
                            Thread.sleep(1000);
                            deliver(drone, order);
                        } catch (InterruptedException i) {}
                    }
                }
            }
        }
        drone.setStatus("Flying");
        drone.setDestination(order.getDestination());
        drone.setProgress(0);
        drone.setDestination(order.getDestination());
        order.getDestination().calculateDistance(server.getRestaurantPostcode());
        try {
            server.getRestaurantPostcode().calculateDistance(order.getDestination());
            Double distance = server.getRestaurantPostcode().getDistance().doubleValue();
            Double speed = Double.valueOf(drone.getSpeed().toString());
            Double time = distance/speed;
            Double originalTime = time;
            while(time > 0) {
                Thread.sleep(1000);
                time -= 1;
                drone.setProgress(100 - ((time/originalTime) * 100));
            }
        } catch (InterruptedException ie) {}
        order.setDelivered();
        drone.setProgress(null);
        drone.setDestination(null);
        drone.setStatus("Idle");
        drone.checkBattery();
    }

}
