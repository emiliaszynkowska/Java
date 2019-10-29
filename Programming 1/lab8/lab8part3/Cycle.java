/* Create the Cycle class
 * Is a subclass of RoadVehicle
 */
public abstract class Cycle extends RoadVehicle {

/* Initialise instance variables
 */
    private Boolean hasStabilisers = false;

/* Class constructor
 * Takes the parameters name, roadType, speed, wheels, seats, operator, manufacturer
 * Set these to the instance variables
 */   
    public Cycle(String name, String roadType, int speed, int wheels, int seats, String operator, String manufacturer) {
        super(name, roadType, speed, wheels, seats, operator, manufacturer);
    }

/* Method go() allows the Cycle to travel
 * Multiply the time by the speed to get a value for distance travelled
 * Print a statement to show this
 */
    public void go(int hours) {
        System.out.println(this.getName() + " cycled " + (this.speed * hours) + " miles");
        System.out.println(" ");
    }

/* Method setStabilisers() gives the Cycle stabilisers
 */
    public void setStabilisers() {
        this.hasStabilisers = true;
    }

    public Boolean getStabilisers() {
        return this.hasStabilisers;
    }

/* Method printAll() prints information about the Cycle
 * Is different for each subclass depending on its instance variables
 */
    public abstract void printAll();

}