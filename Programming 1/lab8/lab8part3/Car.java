/* Create the Car class
 * Is a subclass of RoadVehicle and is Refuelable
 */
public class Car extends RoadVehicle implements Refuelable {

/* Initialise instance variables
 * A car has an engine, can be parked, and can pass MOT testing
 */
    private String engine;
    private Boolean isParked = false;
    private Boolean passedMOT = false;

    public int fuelLevel;
    public Boolean needsFuel;
    public String fuelType;

/* Constructor for car
 * Takes the parameters name, engine, roadType, speed, wheels, seats, operator, manufacturer
 * Set these to the instance variables
 */
    public Car(String name, String engine, String roadType, int speed, int wheels, int seats, String operator, String manufacturer) {
        super(name, roadType, speed, wheels, seats, operator, manufacturer);
        this.engine = engine;
    }

/* Getters, return values
 */
    public String getEngine() {
        return engine;
    }

    public Boolean getIsParked() {
        return isParked;
    }

    public Boolean getPassedMOT() {
        return passedMOT;
    }

/* Method getMOT() gets an MOT test for the Car
 * Uses a boolean value
 */
    public void getMOT() {
        this.passedMOT = true;
    }

/* Method park() sets a boolean value for parking
 */
    public void park() {
        this.isParked = true;
    }

/* Method printAll() prints information about the Car
 */
    public void printAll() {
        System.out.println("Information about " + this.getName() + " : ");
        System.out.println("Name: " + this.getName());
        System.out.println("Manual or automatic: " + this.getEngine());
        System.out.println("Drives on: " + this.getRoadType());
        System.out.println("Speed: " + this.getSpeed() + "mph");
        System.out.println("Wheels: " + this.getWheels());
        System.out.println("Seats: " + this.getSeats());
        System.out.println("Operator: " + this.getOperator());
        System.out.println("Manufacturer: " + this.getManufacturer());
        Boolean x = this.getIsParked();
        if(x = true) {
            System.out.println(this.getName() + " is parked");
        }
        else {
            System.out.println(this.getName() + " is not parked");
        }
        Boolean y = this.getPassedMOT();
        if(y = true) {
            System.out.println("MOT passed");
        }
        else {
            System.out.println("MOT failed");
        }
        System.out.println("Fuel type: " + this.fuelType);
        System.out.println("Fuel level: " + this.fuelLevel);
        System.out.println(" ");
    }

/* Method go() allows the car to drive
 * Multiply the time by the speed in mph to get a value for miles travelled
 * Print a statement to show the distance travelled
 * Use fuel to travel and update the value of fuelLevel to reflect this
 * Tell the user if the fuel level is too low
 */
    public void go(int hours) {
        int fuelNeeded = hours * this.speed;
        if(this.fuelLevel >= fuelNeeded) {
            System.out.println(this.getName() + " travelled " + this.speed * hours + " miles");
            System.out.println(" ");
            this.fuelLevel -= fuelNeeded;
        }
        else {
            System.out.println("Not enough fuel. Refill the fuel");
            System.out.println(" ");
            this.needsFuel = true;
        }
    }

/* Method initialiseFuel() sets the starting value of fuelLevel
 */
    public void initialiseFuel() {
        fuelLevel = 600;
        needsFuel = false;
    }

/* Method setFuelType() sets the String fuelType
 */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

/* Method reFuel() adds to the value of fuelLevel
 */
    public void reFuel(int fuel) {
        fuelLevel += fuel;
        System.out.println(fuel + " litres of fuel added");
        System.out.println(" ");
        this.needsFuel = false;
    }

}