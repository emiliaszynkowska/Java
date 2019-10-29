/* Create the interface Refuelable
 * All the methods are abstract
 * This class acts as a template for Refuelable vehicles
 */
public interface Refuelable {

/* Methods initialiseFuel(), reFuel() and setFuelType() which will be described in the classes
 * These will allow the fuel to have a type and a value, and to be filled when it has been used up
 * initialiseFuel() = set a number for the fuel level
 * reFuel() = add to the fuel number
 * setFuelType() = set a string for the type of fuel
 */
    public abstract void initialiseFuel();
    public abstract void reFuel();
    public abstract void setFuelType();

}