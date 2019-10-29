package comp1206.sushi.server;

import comp1206.sushi.server.Server;
import comp1206.sushi.common.*;
import java.io.*;
import java.util.Map;

public class DataPersistenceWriter implements Runnable {

    File file;
    FileWriter fileWriter;
    BufferedWriter writer;
    Server server;

    public DataPersistenceWriter(Server server) {
        this.server = server;
    }

    public void run() {

        while(true) {
            try {
                file = new File("data.txt");
                fileWriter = new FileWriter(file);
                writer = new BufferedWriter(fileWriter);

                writer.write("RESTAURANT:"+server.getRestaurant().getName()+":"+server.getRestaurantPostcode());
                writer.write("\n");
                writer.flush();

                for(Postcode p : server.getPostcodes()) {
                    writer.write("POSTCODE:"+p.getName());
                    writer.write("\n");
                    writer.flush();
                }
                for(Supplier s : server.getSuppliers()) {
                    writer.write("SUPPLIER:"+s.getName()+":"+s.getPostcode());
                    writer.write("\n");
                    writer.flush();
                }
                for(Ingredient i : server.getIngredients()) {
                    writer.write("INGREDIENT:"+i.getName()+":"+i.getUnit()+":"+i.getSupplier().getName()+":"+i.getRestockThreshold()+":"+i.getRestockAmount()+":"+i.getWeight());
                    writer.write("\n");
                    writer.flush();
                }
                for(Ingredient i : server.getIngredients()) {
                    String name = i.getName();
                    Integer stock = (Integer)i.getStock();
                    writer.write("STOCK:"+name+":"+stock);
                    writer.write("\n");
                    writer.flush();
                }
                for(Dish d : server.getDishes()) {
                    Integer quantity;
                    String name;
                    String recipe = "";
                    for(Map.Entry e : d.getRecipe().entrySet()) {
                        quantity = (Integer)e.getValue();
                        name = ((Ingredient)e.getKey()).getName();
                        recipe += quantity + " * " + name + ",";
                    }
                    if(recipe.length() > 0) {
                        char last = recipe.charAt(recipe.length() - 1);
                        if (last == ',') {
                            recipe = recipe.substring(0, recipe.length() - 1);
                        }
                    }
                    writer.write("DISH:"+d.getName()+":"+d.getDescription()+":"+d.getPrice()+":"+d.getRestockThreshold()+":"+d.getRestockAmount()+":"+recipe);
                    writer.write("\n");
                    writer.flush();
                }
                for(Dish d : server.getDishes()) {
                    String name = d.getName();
                    Integer stock = (Integer)d.getStock();
                    writer.write("STOCK:"+name+":"+stock);
                    writer.write("\n");
                    writer.flush();
                }
                for(User u : server.getUsers()) {
                    writer.write("USER:"+u.getName()+":"+u.getPassword()+":"+u.getLocation()+":"+u.getPostcode().getName());
                    writer.write("\n");
                    writer.flush();
                }
                for(Staff s : server.getStaff()) {
                    writer.write("STAFF:"+s.getName());
                    writer.write("\n");
                    writer.flush();
                }
                for(Drone d : server.getDrones()) {
                    writer.write("DRONE:"+d.getSpeed());
                    writer.write("\n");
                    writer.flush();
                }
                for(Order o : server.getOrders()) {
                    Integer quantity;
                    String dish;
                    String contents = "";
                    for(Map.Entry e : o.getContents().entrySet()) {
                        quantity = (Integer)e.getValue();
                        dish = ((Dish)e.getKey()).getName();
                        contents += quantity + " * " + dish + ",";
                    }
                    if(contents.length() > 0) {
                        char last = contents.charAt(contents.length() - 1);
                        if (last == ',') {
                            contents = contents.substring(0, contents.length() - 1);
                        }
                    }
                    writer.write("ORDER:"+o.getUser().getName()+":"+contents);
                    writer.write("\n");
                    writer.flush();
                }

                Thread.sleep(5000);
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException i) {}
        }
    }

}
