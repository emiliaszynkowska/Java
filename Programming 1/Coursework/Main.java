import java.util.*;
import java.io.*;

/* Main class
 */
public class Main {

/* Create a house myHouse
 * Create an appliance generator myGenerator
 */
    House myHouse;
    ApplianceGenerator myGenerator;

    public static void main(String[] args) {

/* The appliance generator reads myHouse.txt and creates an ArrayList containing all the text from the file
 * The appliance generator creates an ArrayList of text for each appliance
 * The appliance generator scans the text to find the information it needs 
 * Creates appliances using the file
 * Adds the appliances to myHouse
 */
        House myHouse = new House("myHouse.txt");
        ApplianceGenerator myGenerator = new ApplianceGenerator("myHouse.txt");
        myGenerator.createArrayLists();
        myGenerator.getValues();

        for(Appliance appliance : myGenerator.apps) {
            if(appliance.getType().contains("electric")) {
                myHouse.addElectricAppliance(appliance);
            }
            if(appliance.getType().contains("water")) {
                myHouse.addWaterAppliance(appliance);
            }
        }

/* Main menu
 * Has 5 options:
 * 1. Activate the house
 * 2. See the current appliances
 * 3. Add a new appliance
 * 4. See usage history
 * 5. Quit
 */
        System.out.println("\n");
        System.out.println("Welcome to SmartHome simulator");
        while(true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Activate the house");
            System.out.println("2. See the current appliances");
            System.out.println("3. Add a new appliance");
            System.out.println("4. See usage history");
            System.out.println("5. Quit");

/* Gets the user input
 * Has to be 1 2 3 4 or 5
 */
            Toolbox myToolbox = new Toolbox();
            String option = myToolbox.readStringFromCmd();

/* Option 1 - Activate the house
 * Default activate - runs for 7 days (168 hours)
 * Activates for 7 days
 * Saves the date, time and cost to the save file
 * Custom activate - runs for a specified time
 * Activates for the number of hours given by the user
 * Saves the date, time and cost the the save file
 * Loops if the input is not 1 or 2
 */       
            if(option.equals("1")) {      
                while(true) {
                    System.out.println("\n" + "What would you like to do?");
                    System.out.println("1. Default activate");
                    System.out.println("2. Custom activate");
                    option = myToolbox.readStringFromCmd();
                    if(option.equals("1")) {
                        myHouse.activate();
                        myGenerator.save("Date: " + java.time.LocalDate.now() + "\n");
                        myGenerator.save("Activated for: 168 hours" + "\n");
                        double saveCost = (myHouse.batteryMeter.getCost() + myHouse.waterMeter.getCost());
                        saveCost = Math.round(saveCost*100.0)/100.00;
                        myGenerator.save("Total cost for this time: " + saveCost + "\n" + "\n");
                        break;
                    }
                    else if(option.equals("2")) {
                        System.out.println("\n" + "How many hours will you activate for?");
                        Integer time = myToolbox.readIntegerFromCmd();
                        myHouse.activate(time);
                        myGenerator.save("Date: " + java.time.LocalDate.now() + "\n");
                        myGenerator.save("Activated for: " + time + " hours" + "\n");
                        double saveCost = (myHouse.finalCost);
                        saveCost = Math.round(saveCost*100.0)/100.00;
                        myGenerator.save("Total cost for this time: " + saveCost + "\n" + "\n");
                        break;
                    }
                    else {
                        System.out.println("\n" + "This is not an option. Try again");
                    }
                }
            }
/* Option 2 - See the current appliances
 * Prints the name, meter and type for each appliance in myHouse
 */
            else if(option.equals("2")) {
                System.out.println("\n" + "There are " + myHouse.numAppliances() + " appliances: " + "\n");
                for(Appliance appliance : myHouse.appliances) {
                    System.out.println("Name: " + appliance.getName());
                    System.out.println("Meter: " + appliance.getType());
                    System.out.println("Type: " + appliance.getClass().getName() + "\n");
                }
            }
/* Option 3 - Add a new appliance
 * Adds an appliance to the config file
 * See the addAppliance() method in ApplianceGenerator
 */
            else if(option.equals("3")) {
                myGenerator.addAppliance();
            }
/* Option 4 - See usage history
 * Prints out the text from the save file
 * This contains the date, time, and cost
 * See the save() and printSave() methods in ApplianceGenerator 
 */
            else if(option.equals("4")) {
                myGenerator.printSave();
            }
/* Option 5 - Quit
 * Quits the program
 */
            else if(option.equals("5")) {
                System.out.println("\n" + "Quitting...");
                System.exit(0);
            } 
            else {
/* The input is not valid
 * The menu will ask for user input again until the user chooses to quit
 * It is important to handle errors so java does not throw exceptions 
 */
                System.out.println("\n" + "This is not an option. Try again" + "\n");
            }
        }
    }
}   
