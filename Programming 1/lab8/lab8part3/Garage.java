import java.util.*;

/* Create the Garage class
 * Stores all the Transport
 */
public class Garage {

/* Create an ArrayList of Transport called transports
 */
    ArrayList<Transport> transports = new ArrayList<Transport>();

/* Class constructor 
 */
    public Garage() {
    }

/* Method addToGarage() adds Transport to the ArrayList transports
 */
    public void addToGarage(Transport transport) {
        transports.add(transport);
    }

/* Method printOut() prints each Transport's name
 */
    public void printOut() {
        System.out.println("The garage contains: ");
        for(Transport transport : transports) {
            System.out.println(transport.getName());
        }
        System.out.println(" ");
    }

/* Method printOutPlus() prints out full details for the garage
 * The printAll() method prints all the instance variables for each Transport
 */
    public void printOutPlus() {
        System.out.println("Garage details: ");
        System.out.println(" ");
        for(Transport transport : transports) {
            transport.printAll();
        }
        System.out.println(" ");
    }
    
}