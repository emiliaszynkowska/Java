/* Create the RoadVehicle class
 * It is a subclass of Transport
 */
public abstract class RoadVehicle extends Transport {

/* Has the instance variable roadType
 */
    protected String roadType;

/* Constructor for RoadVehicle
 * Takes the parameters name, roadType, speed, wheels, seats, operator, manufacturer
 * Set these to the instance variables
 */  
    public RoadVehicle(String name, String roadType, int speed, int wheels, int seats, String operator, String manufacturer) {
        super(name, speed, wheels, seats, operator, manufacturer);
        this.roadType = roadType;
    }

    public String getRoadType() {
        return roadType;
    }

/* Method go() allows the RoadVehicle to travel
 * Multiply the time by the speed to get a value for distance travelled
 * Print a statement to show this
 */
    public void go(int hours) {
        System.out.println(this.getName() + " travelled " + this.speed * hours + " miles");
        System.out.println(" ");
    }

/* Method printAll() prints information about the RoadVehicle
 * Is different for each subclass depending on its instance variables
 */
    public abstract void printAll();

}