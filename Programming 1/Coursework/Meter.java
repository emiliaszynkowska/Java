import java.util.*;

/* Meter class
 */
public class Meter {

/* Instance variables:
 * utilityName - name of the meter
 * unitCost - cost in £ per unit used
 * meterReading - number of units used
 * finalCost - variable used to store the costs when running report()
 */
  protected String utilityName;
  protected double unitCost;
  protected float meterReading = 0;
  protected double finalCost;

/* Constructor
 * Takes the parameters utilityName and unitCost
 */
  public Meter(String utilityName, double unitCost) {
    this.utilityName = utilityName;
    this.unitCost = unitCost;
  }

/* Method consumeUnits()
 * Takes the double parameter reading
 * Adds reading to meterReading
 */
  public void consumeUnits(double reading) {
    meterReading += (float) reading;
  }

/* Getter for finalCost
 */
  public double getCost() {
    return this.finalCost;
  }

/* Method report()
 * Prints out the name and reading
 * Calculates the cost by doing meterReading * unitCost => number of units * cost of one unit
 * Stores and prints out the cost
 */
  public void report() {
    System.out.println("Utility: " + utilityName);
    System.out.println("Reading: " + meterReading);
    double cost = meterReading * unitCost;
    cost = Math.round(cost*100.0)/100.00;
    this.finalCost = cost;
    System.out.println("Cost: " + "£" + (cost));
    System.out.println(" ");
    meterReading = 0;
  }

}
