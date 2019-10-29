import java.util.*;
import java.io.*;

/* ApplianceGenerator class
 */
public class ApplianceGenerator {

/* Instance variables:
 * apps - a list of all the appliances from the config file
 * applianceAdder - a PrintSream which writes to myHouse.txt. When the user chooses "Option 3. Add a new appliance" in the main method, the applianceAdder writes a new appliance into the file 
 * saveResults - a PrintSream which writes to save.txt. Every time the house is activated, the date, time activated, and cost are written to the save file
 */
    ArrayList<Appliance> apps = new ArrayList<Appliance>();
    private PrintStream applianceAdder;
    private PrintStream saveResults;

/* filename - the name of the config file, in this case it is myHouse.txt
 * reader - a BufferedReader which reads the config file and gets information from it
 * lines - a list of all the text in the config file
 * allArrayLists - a list of text for each appliance. The 'lines' ArrayList is split in groups of 9 lines. This means there is a separate ArrayList for each appliance
 * property - each line in the config file has a two parts, before and after the colon. The first part is the property and the second part is the value
 */
    String filename;
    BufferedReader reader;
    ArrayList<String> lines;
    ArrayList<ArrayList<String>> allArrayLists;
    String property;

/* These are the properties found in the config file
 * ApplianceGenerator reads through the config file and gets all of these values
 */
    String name;
    String subclass;
    String meter;
    Double fixedUnits;
    Integer cycleLength;
    Integer minUnits;
    Integer maxUnits;
    Double probability;

/* Constructor
 * Takes a String parameter for the name of the config file
 * Initialises the save file
 * PrintStream empties the file every time it writes to it, however we want the save file to keep any previously added data
 * It is necessary to store and re-add any existing data in the file 
 * The BufferedReader saved reads the existing data and stores it in the ArrayList saveInfo, then the PrintStream saveResults takes the info and writes it to the empty file
 * Exceptions are caught
 */
    public ApplianceGenerator(String filename) {
        this.filename = filename;
        try {
            ArrayList<String> saveInfo = new ArrayList<String>();
            BufferedReader saved = new BufferedReader(new FileReader("save.txt"));
            String currentLine = saved.readLine();
            while(currentLine != null) {
                saveInfo.add(currentLine);
                currentLine = saved.readLine();
            }
            saved.close();
            saveResults = new PrintStream(new FileOutputStream("save.txt"));
            for(String info : saveInfo) {
                saveResults.append(info);
                saveResults.append("\n");
            }
        }
        catch(Exception e) {
            System.out.println("Error");
        }
    }

/* String getLine()
 * The BufferedReader reader reads the config file
 * Reads one line of the file
 * Exceptions are caught
 * NOTE: This method has been used from my submission for Programming 1 Lab 9, in this lab I was asked to make a getLine() method similar to the one below
 */
    public String getLine() {
        try {
            return reader.readLine();
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage() + " Error reading file");
        }
        return " ";
    }

/* Method createArrayLists()
 * Reads the text in the config file
 * Adds the text to an ArrayList called lines
 * Splits lines into blocks of 9 lines and creates an ArrayList for each
 * Now each appliance has its own ArrayList of data
 */
    public void createArrayLists() {
        lines = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage() + " The file was not found");
        }

        String currentLine = getLine();
        while(currentLine != null) {
        lines.add(currentLine);
        currentLine = getLine();
        }

        allArrayLists = new ArrayList<ArrayList<String>>();
        int n = lines.size();
        for(int i = 0; i < n; i += 9) {
        allArrayLists.add(new ArrayList<String>(lines.subList(i, Math.min(n, i + 9))));
        }
    }

/* Method getValues()
 * This method looks through the data and gets the relevant information from it
 * Each line is split into a property and value
 * The property is checked
 * The value is assigned to the correct instance variable
 * The value is converted to the correct type e.g. String, Integer, Double
 */ 
    public void getValues() {
        for(ArrayList<String> arrayList : allArrayLists) {
            for(String line : arrayList) {
                String[] splitLine = line.split(": ");
                String property = splitLine[0];
                try {
                    String value = splitLine[1];
                    if(property.contains("name")) {
                        this.name = value;
                    }
                    if(property.contains("subclass")) {
                        this.subclass = value;
                    }
                    if(property.contains("meter")) {
                        this.meter = value;
                    }
                    if(property.contains("Fixed units")) {
                        try {
                            Double doubleValue = Double.parseDouble(value);
                            this.fixedUnits = doubleValue;
                        }
                        catch (NumberFormatException numberFormatException) {
                        }
                    }
                    if(property.contains("Cycle length")) {
                        try {
                            String[] string = value.split("/");
                            Integer intValue = Integer.parseInt(string[0]);
                            this.cycleLength = intValue;
                        }
                        catch (NumberFormatException numberFormatException) {
                        }
                    }
                    if(property.contains("Min units")) {
                        try {
                            Integer intValue = Integer.parseInt(value);
                            this.minUnits = intValue;
                        }
                        catch (NumberFormatException numberFormatException) {
                        }
                    }
                    if(property.contains("Max units")) {
                        try {
                            Integer intValue = Integer.parseInt(value);
                            this.maxUnits = intValue;
                        }
                        catch (NumberFormatException numberFormatException) {
                        }
                    }
                    if(property.contains("Probability")) {
                        try {
                            String[] string = value.split("in");
                            Double doubleValue = Double.parseDouble(string[1]);
                            this.probability = doubleValue;
                        }
                        catch (NumberFormatException numberFormatException) {
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                }
            }

/* Depending on the given values, a CyclicFixed, CyclicVaries, RandomFixed or RandomVaries appliance is created
 * The appliance is added to the list apps
 */
            if((fixedUnits != null) && (cycleLength != null)) {
                Appliance appliance = new CyclicFixed(fixedUnits, cycleLength);
                appliance.setName(name);
                appliance.setType(meter);
                apps.add(appliance);
            }
            else if((minUnits != null) && (maxUnits != null) && (cycleLength != null)) {
                Appliance appliance = new CyclicVaries(minUnits, maxUnits, cycleLength);
                appliance.setName(name);
                appliance.setType(meter);
                apps.add(appliance);
            }
            else if((fixedUnits != null) && (probability != null)) {
                Appliance appliance = new RandomFixed(fixedUnits, probability);
                appliance.setName(name);
                appliance.setType(meter);
                apps.add(appliance);
            }
            else if((minUnits != null) && (maxUnits != null) && (probability != null)) {
                Appliance appliance = new RandomVaries(minUnits, maxUnits, probability);
                appliance.setName(name);
                appliance.setType(meter);
                apps.add(appliance);
            }
/* The instance variables are set back to normal
 */
            name = null;
            subclass = null;
            meter = null;
            fixedUnits = null;
            cycleLength = null;
            minUnits = null;
            maxUnits = null;
            probability = null;
        }
    }

/* Method addAppliance
 * Writes a new appliance to the config file
 * Uses Toolbox to get values from the user
 * The user must input the name, subclass, meter, fixed units, cycle length, min units, max units, and probability
 * The user's inputs are stored as instance variables
 * Exceptions are caught
 */
    public void addAppliance() {
        Toolbox myToolbox = new Toolbox();
        System.out.println("Please enter the relevant values");

        try {
            System.out.println("Name: ");
            name = myToolbox.readStringFromCmd();

            System.out.println("Subclass: ");
            subclass = myToolbox.readStringFromCmd();

            System.out.println("Meter: ");
            meter = myToolbox.readStringFromCmd();

            System.out.println("Fixed Units: ");
            String fixedUnitsString = myToolbox.readStringFromCmd();
            fixedUnits = Double.parseDouble(fixedUnitsString);

            System.out.println("Cycle Length: ");
            String cycleLengthInt = myToolbox.readStringFromCmd();
            cycleLength = Integer.parseInt(cycleLengthInt);

            System.out.println("Min Units: ");
            String minUnitsInt = myToolbox.readStringFromCmd();
            minUnits = Integer.parseInt(minUnitsInt);

            System.out.println("Max Units: ");
            String maxUnitsInt = myToolbox.readStringFromCmd();
            maxUnits = Integer.parseInt(maxUnitsInt);

            System.out.println("Probability: ");
            String probabilityString = myToolbox.readStringFromCmd();
            probability = Double.parseDouble(probabilityString);
        }
        catch (Exception e) {
            System.out.println("Error. Enter the correct type of value \n");
        }

/* A new BufferedReader apps is created
 * This gets all the existing text and re-adds it to the config file
 * A new PrintStream applianceAdder is created
 * Writes the data into the config file
 * Exceptions are caught
 */
        try {
            ArrayList<String> text = new ArrayList<String>();
            BufferedReader apps = new BufferedReader(new FileReader("myHouse.txt"));
            String currentLine = apps.readLine();
            while(currentLine != null) {
                text.add(currentLine);
                currentLine = apps.readLine();
            }
            apps.close();
            applianceAdder = new PrintStream(new FileOutputStream("myHouse.txt"));
            for(String string : text) {
                applianceAdder.append(string);
                applianceAdder.append("\n");
            }
            applianceAdder.append("\n");
            applianceAdder.append("name: " + name);
            applianceAdder.append("\n");
            applianceAdder.append("subclass: " + subclass);
            applianceAdder.append("\n");
            applianceAdder.append("meter: " + meter);
            applianceAdder.append("\n");
            applianceAdder.append("Min units consumed: " + minUnits);
            applianceAdder.append("\n");
            applianceAdder.append("Max units consumed: " + maxUnits);
            applianceAdder.append("\n");
            applianceAdder.append("Fixed units consumed: " + fixedUnits);
            applianceAdder.append("\n");
            applianceAdder.append("Probability switched on: " + probability);
            applianceAdder.append("\n");
            applianceAdder.append("Cycle length: " + cycleLength);
            applianceAdder.append("\n");
        }
        catch(Exception e) {
            System.out.println("Error");
        }
        applianceAdder.close();
    }

/* Method save()
 * Writes save data to the save file
 */
    public void save(String string) {
        saveResults.append(string);
    }

/* Method printSave()
 * A BufferedReader reader is created
 * Reads and prints all the save data
 */
    public void printSave() {
        ArrayList<String> text = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader("save.txt"));
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage() + " The file was not found");
        }

        String currentLine = getLine();
        while(currentLine != null) {
        text.add(currentLine);
        currentLine = getLine();
        }

        System.out.println("\n" + "Usage history: \n");
        for(String string : text) {
            System.out.println(string);
        }
    }

}