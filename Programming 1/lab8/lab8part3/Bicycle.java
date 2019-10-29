/* Create the Bicycle class
 * Is a subclass of Cycle
 */
public class Bicycle extends Cycle {

/* Initialise the instance variables hasSpokes and spokesColour
 */
    private Boolean hasSpokes = false;
    private String spokesColour;
    
/* Constructor for Bicycle
 * Takes the parameters name, roadType, speed, wheels, seats, operator, manufacturer
 * Set these to the instance variables
 */
    public Bicycle(String name, String roadType, int speed, int wheels, int seats, String operator, String manufacturer) {
        super(name, roadType, speed, wheels, seats, operator, manufacturer);
    }

/* Getters and setters
 */
    public void setSpokes() {
        this.hasSpokes = true;
    }

    public void setSpokesColour(String colour) {
        this.spokesColour = colour;
    }

    public Boolean getHasSpokes() {
        return this.hasSpokes;
    }

    public String getSpokeColour() {
        return this.spokesColour;
    }

/* Method printAll() prints information about the Bicycle
 * The Bicycle can have stabilisers and spokes
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
        Boolean x = this.getStabilisers();
        if(x = true) {
            System.out.println(this.getName() + " has stabilisers");
        }
        else {
            System.out.println(this.getName() + " does not have stabilisers");
        }
        Boolean y = this.getHasSpokes();
        if(y = true) {
            System.out.println(this.getName() + " has spokes");
            System.out.println("The spokes are " + this.getSpokeColour());
        }
        else {
            System.out.println(this.getName() + " does not have spokes");
        }
        System.out.println(" ");
    }

}