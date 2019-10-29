/* Battery class
 * The Battery holds extra charge produced by the BatteryMeter
 * This is produced by energy-producing appliances such as a wind turbine or a generator
 */
public class Battery {

/* Initialise the instance variable charge
 * The battery gets a starting charge of 1000 units
 */
  private double charge = 1000.0;

/* Constructor
 */
  public Battery() {
  }

/* Method addCharge()
 * Takes the double parameter charge
 * Adds charge to the battery
 * The battery has a limit of 5000 units
 */
  public void addCharge(double charge) {
    if((this.charge + charge) <= 5000.0) {
      this.charge += charge;
    }
    else {
      this.charge = 5000;
    }
  }

/* Method subCharge
 * Subtracts charge form the battery
 */
  public void subCharge(double charge) {
    this.charge -= charge;
  }

/* Getter and setter
 */
  public double getCharge() {
    return this.charge;
  }

  public void setCharge(double charge) {
    this.charge = charge;
  }

}
