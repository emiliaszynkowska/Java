/* Create the abstract class Transport
 * Cannot be instantiated, but provides methods for the subclasses
 * This reduces code duplication and increases efficiency, also keeps classes organised
 */
public abstract class Transport {

/* Initialise the instance variables:
 * name = name of the transport 
 * speed = speed in miles per hour
 * wheels = number of wheels
 * seats = number of seats
 * operator = name of the driver of the vehicle 
 * manufacturer = name of the company which made the vehicle
 * 'protected' means that this class AND the subclasses can access these variables
 */
    protected String name;
    protected int speed; 
    protected int wheels;
    protected int seats;
    protected String operator;
    protected String manufacturer;
    
/* Constructor for the class Transport
 * Takes the parameters name, speed, wheels, seats, operator, manufacturer
 * Set them to the instance variables
 */
    public Transport(String name, int speed, int wheels, int seats, String operator, String manufacturer) {
        this.name = name;
        this.speed = speed;
        this.wheels = wheels;
        this.seats = seats;
        this.operator = operator;
        this.manufacturer = manufacturer;
    }

/* Getters, return instance variables
 */
    public String getName() {
        return this.name;
    }
    public int getSpeed() {
        return speed;
    }
    public int getWheels() {
        return wheels;
    }
    public int getSeats() {
        return seats;
    }
    public String getOperator() {
        return operator;
    }
    public String getManufacturer() {
        return manufacturer;
    }

/* Abstract method go() which will be described within the subclasses
 * Is different depending if the class is Refuelable or not
 */
    public abstract void go(int hours);

/* Abstract method printAll() which is different for each subclass depending on the instance variables it has
 */
    public abstract void printAll();

}
