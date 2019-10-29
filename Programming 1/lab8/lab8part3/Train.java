/* Create the Train class
 * Is a subclass of Transport and is Refuelable
 */
public class Train extends Transport implements Refuelable {

/* Set instance variables
 * A train has carriages and a line
 */
    private int carriages;
    private String line;
    public int fuelLevel;
    public Boolean needsFuel;
    public String fuelType;

/* Constructor for Train
 * Takes the parameters name, speed, wheels, seats, carriages, line, operator, manfacturer
 * Set these to the instance variables
 */
    public Train(String name, int speed, int wheels, int seats, int carriages, String line, String operator, String manufacturer) {
        super(name, speed, wheels, seats, operator, manufacturer);
        this.carriages = carriages;
        this.line = line;
    }

/* Getters, return values
 */
    public int getCarriages() {
        return carriages;
    }
    public String getLine() {
        return line;
    }

/* Method go() allows the Train to travel
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
            fuelLevel = 1000;
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

/* Method printAll() prints information about the Train
 */
    public void printAll() {
        System.out.println("Information about " + this.getName() + " : ");
        System.out.println("Name: " + this.getName());
        System.out.println("Speed: " + this.getSpeed() + "mph");
        System.out.println("Wheels: " + this.getWheels());
        System.out.println("Seats: " + this.getSeats());
        System.out.println("Carriages: " + this.getCarriages());
        System.out.println("Line: " + this.getLine());
        System.out.println("Operator: " + this.getOperator());
        System.out.println("Manufacturer: " + this.getManufacturer());
        System.out.println("Fuel type: " + this.fuelType);
        System.out.println("Fuel level: " + this.fuelLevel);
        System.out.println(" ");
    }

}