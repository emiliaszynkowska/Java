package comp1206.sushi.server;

import comp1206.sushi.common.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;

public class Configuration {

    Server server;
    String line;
    String filename;
    FileReader fileReader;
    BufferedReader bufferedReader;

    Postcode restaurantPostcode;
    String restaurantName;

    public Configuration(Server server, String filename) {
        this.server = server;
        this.filename = filename;
    }

    public void getRestaurant() {
        try {
            File file = new File(filename);
            if(file.exists() && file.length() > 0) {
                fileReader = new FileReader(filename);
                bufferedReader = new BufferedReader(fileReader);
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.split(":")[0].equals("RESTAURANT")) {
                        restaurantPostcode = new Postcode(line.split(":")[2]);
                        restaurantName = line.split(":")[1];
                        break;
                    }
                }
                bufferedReader.close();
            }
            if(server.restaurant == null) {
                server.restaurant = new Restaurant("Restaurant", new Postcode("SO17 1BJ"));
                server.addPostcode("SO17 1BJ");
                restaurantPostcode = new Postcode("SO17 1BJ");
                restaurantName = "Restaurant";
            }
        } catch (Exception e) {}
    }

    public void read() {
        try {
            File file = new File(filename);
            if(file.exists() && file.length() > 0) {
                fileReader = new FileReader(filename);
                bufferedReader = new BufferedReader(fileReader);
                while ((line = bufferedReader.readLine()) != null) {
                    switch (line.split(":")[0]) {
                        case ("POSTCODE"):
                            String postcodeInput = line.split(":")[1];
                            for (Postcode p : server.getPostcodes()) {
                                if (p.getName().equals(postcodeInput)) {
                                    server.removePostcode(p);
                                    break;
                                }
                            }
                            server.addPostcode(postcodeInput);
                            break;
                        case ("SUPPLIER"):
                            String supplierInput = line.split(":")[1];
                            for (Supplier s : server.getSuppliers()) {
                                if (s.getName().equals(supplierInput)) {
                                    server.removeSupplier(s);
                                    break;
                                }
                            }
                            String supplierPostcode = line.split(":")[2];
                            for (Postcode p : server.getPostcodes()) {
                                if (p.getName().equals(supplierPostcode)) {
                                    server.addSupplier(supplierInput, p);
                                    break;
                                } else {
                                    server.addSupplier(supplierInput, new Postcode(supplierPostcode));
                                    break;
                                }
                            }
                            break;
                        case ("INGREDIENT"):
                            for (Ingredient i : server.getIngredients()) {
                                if (i.getName().equals(line.split(":")[1])) {
                                    server.removeIngredient(i);
                                    break;
                                }
                            }
                            String ingredientSupplier = line.split(":")[3];
                            for (Supplier s : server.getSuppliers()) {
                                if (s.getName().equals(ingredientSupplier)) {
                                    server.addIngredient(line.split(":")[1], line.split(":")[2], s, Integer.parseInt(line.split(":")[4]), Integer.parseInt(line.split(":")[5]), Integer.parseInt(line.split(":")[6]));
                                    break;
                                }
                            }
                            break;
                        case ("DISH"):
                            String dishName = line.split(":")[1];
                            for (Dish d : server.getDishes()) {
                                if (d.getName().equals(dishName)) {
                                    server.removeDish(d);
                                }
                            }

                            HashMap<Ingredient, Number> recipeMap = new HashMap<Ingredient,Number>();
                            try {
                                String dishRecipe = line.split(":")[6];

                                ArrayList<String> recipeArrayList = new ArrayList<String>();
                                String[] recipeArray = dishRecipe.split(",");
                                for (String string : recipeArray) {
                                    recipeArrayList.add(string);
                                }

                                recipeMap = new HashMap<Ingredient, Number>();
                                for (String string : recipeArrayList) {
                                    String[] line = string.split("\\*");
                                    Integer number = Integer.parseInt(line[0].trim());
                                    String name = line[1];
                                    name = name.trim();
                                    for (Ingredient i : server.getIngredients()) {
                                        if (i.getName().equals(name))
                                            recipeMap.put(i, number);
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException a) {}

                            Dish dish = new Dish(dishName, line.split(":")[2], Integer.parseInt(line.split(":")[3]), Integer.parseInt(line.split(":")[4]), Integer.parseInt(line.split(":")[5]));
                            dish.setRecipe(recipeMap);
                            server.dishes.add(dish);
                            break;
                        case ("STAFF"):
                            String staffInput = line.split(":")[1];
                            for (Staff s : server.getStaff()) {
                                if (s.getName().equals(staffInput)) {
                                    server.removeStaff(s);
                                    break;
                                }
                            }
                            server.addStaff(staffInput);
                            break;
                        case ("USER"):
                            String userName = line.split(":")[1];
                            String userPassword = line.split(":")[2];
                            String userAddress = line.split(":")[3];
                            String userPostcode = line.split(":")[4];
                            for (User u : server.getUsers()) {
                                if (u.getName().equals(userName)) {
                                    server.removeUser(u);
                                    break;
                                }
                            }
                            server.addUser(userName, userPassword, userAddress, new Postcode(userPostcode));
                            break;
                        case ("DRONE"):
                            Integer droneInput = Integer.parseInt(line.split(":")[1].trim());
                            for (Drone d : server.getDrones()) {
                                if (d.getSpeed() == droneInput) {
                                    server.removeDrone(d);
                                    break;
                                }
                            }
                            server.addDrone(droneInput);
                            break;
                        case ("ORDER"):
                            String user = line.split(":")[1];
                            Postcode destination = server.getRestaurantPostcode();
                            for (User u : server.getUsers()) {
                                if (u.getName().equals(user)) {
                                    destination = u.getPostcode();
                                }
                            }

                            HashMap<Dish, Number> orderMap = new HashMap<Dish, Number>();

                            try {
                                String order = line.split(":")[2];

                                ArrayList<String> orderArrayList = new ArrayList<String>();
                                String[] orderArray = order.split(",");
                                for (String string : orderArray)
                                    orderArrayList.add(string);

                                orderMap = new HashMap<Dish, Number>();
                                for (String string : orderArrayList) {
                                    Integer number = Integer.parseInt(string.split("\\*")[0].trim());
                                    String name = string.split("\\*")[1];
                                    name = name.trim();
                                    for (Dish d : server.getDishes()) {
                                        if (d.getName().equals(name))
                                            orderMap.put(d, number);
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException a) {}

                            Order mock = new Order();
                            mock.setContents(orderMap);
                            mock.setDestination(destination);
                            for (User u : server.getUsers()) {
                                if (u.getName().equals(user)) {
                                    mock.setUser(u);
                                }
                            }
                            server.orders.add(mock);
                            break;
                        case ("STOCK"):
                            String stockInput1 = line.split(":")[1];
                            Number stockInput2 = Integer.parseInt(line.split(":")[2].trim());
                            for (Dish d : server.getDishes()) {
                                if (d.getName().equals(stockInput1))
                                    server.setStock(d, stockInput2);
                            }
                            for (Ingredient i : server.getIngredients()) {
                                if (i.getName().equals(stockInput1))
                                    server.setStock(i, stockInput2);
                            }
                            break;
                    }
                }
                bufferedReader.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (ServerInterface.UnableToDeleteException u) {}
    }

}