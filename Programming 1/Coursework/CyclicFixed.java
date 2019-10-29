import java.util.*;

/* CyclicFixed class
 * Extends Appliance
 */
public class CyclicFixed extends Appliance {

/* Instance variables:
 * units - number of units used per hour
 * hoursActive - number of hours active out of 24 
 * hoursPassed - number of hours the appliance has been active
 */
  private double units;
  private int hoursActive;
  private double hoursPassed = 0;

/* Constructor
 * Takes the parameters units and hoursActive
 * Checks hoursActive is between 1 and 24
 * Sets units and hoursActive to the instance variables
 */
  public CyclicFixed(double units, int hoursActive) {
    if((hoursActive <= 24) && (hoursActive >= 1)) {
      this.units = units;
      this.hoursActive = hoursActive;
    }
    else {
      System.out.println("Error. Enter a value between 1 and 24");
    }
  }

/* Method timePasses()
 * Takes the int parameter time
 * The appliance runs for this number of hours
 * If time is greater than hoursActive, the appliance takes this into account as it can only run for a certain number of hours per day
 */
  public void timePasses(int time) {
    if(time <= hoursActive) {
      this.hoursPassed = time;
    } else {
      this.hoursPassed = time * (hoursActive/24.0);
    }
  }

/* double tellMeterToConsumeUnits()
 * The number of units is calculated by doing hoursPassed * units => number of hours passed * units used per hour
 */
  public double tellMeterToConsumeUnits() {
    return ((double) this.hoursPassed * this.units);
  }

}
