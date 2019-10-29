/* Create the TriCycle class
 * Is a subclass of Cycle
 */
public class Tricycle extends Cycle {

/* Initialise the instance variables
 */
    private Boolean hasBasket = false;

/* Constructor for TriCycle
 * Takes the parameters name, roadType, speed, wheels, seats, operator, manufacturer
 * Set these to the instance variables
 */   
    public Tricycle(String name, String roadType, int speed, int wheels, int seats, String operator, String manufacturer) {
        super(name, roadType, speed, wheels, seats, operator, manufacturer);
    }

/* Getter for hasBasket
 */
    public Boolean getHasBasket() {
        return this.hasBasket;
    }

/* Method getBasket() gives the Tricycle a basket 
 */
    public void getBasket() {
        this.hasBasket = true;
    }

/* Method printAll() prints information about the TriCycle
 */
    public void printAll() {
        System.out.println("Information about " + this.getName() + " : ");
        System.out.println("Name: " + this.getName());
        System.out.println("Drives on: " + this.getRoadType());
        System.out.println("Speed: " + this.getSpeed() + "mph");
        System.out.println("Wheels: " + this.getWheels());
        System.out.println("Seats: " + this.getSeats());
        System.out.println("Operator: " + this.getOperator());
        System.out.println("Manufacturer: " + this.getManufacturer());
        Boolean x = this.getHasBasket();
        if(x = true) {
            System.out.println(this.getName() + " has a basket");
        }
        else {
            System.out.println(this.getName() + " does not have a basket");
        }
        System.out.println(" ");
    }

}