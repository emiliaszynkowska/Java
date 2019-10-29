/* Create the JetPlane class
 * Is a subclass of Transport and is Refuelable
 */
public class JetPlane extends Transport implements Refuelable {

/* Initialise instance variables
 */
    private int altitude;
    public int fuelLevel;
    public Boolean needsFuel;
    public String fuelType;
    
/* Constructor for JetPlane
 * Takes the parameters name, speed, wheels, seats, altitude, operator, manufacturer
 * Set the parameters to the instance variables
 */
    public JetPlane(String name, int speed, int wheels, int seats, int altitude, String operator, String manufacturer) {
        super(name, speed, wheels, seats, operator, manufacturer);
        this.altitude = altitude;
    }

    public int getAltitude() {
        return altitude;
    }

/* Method printAll() prints information about the JetPlane
 */
    public void printAll() {
        System.out.println("Information about " + this.getName() + " : ");
        System.out.println("Name: " + this.getName());
        System.out.println("Speed: " + this.getSpeed() + "mph");
        System.out.println("Wheels: " + this.getWheels());
        System.out.println("Seats: " + this.getSeats());
        System.out.println("Altitude: " + this.getAltitude() + " metres");
        System.out.println("Operator: " + this.getOperator());
        System.out.println("Manufacturer: " + this.getManufacturer());
        System.out.println("Fuel type: " + this.fuelType);
        System.out.println("Fuel level: " + this.fuelLevel);
        System.out.println(" ");
    }

/* Method go() allows the plane to fly
 * Multiply the time by the speed in mph to get a value for miles travelled
 * Print a statement to show the distance travelled and altitude
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
        fuelLevel = 6000;
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