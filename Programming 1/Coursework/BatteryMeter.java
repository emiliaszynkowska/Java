/* BatteryMeter class
 */
public class BatteryMeter extends Meter {

  private Battery battery;

/* Constructor
 * Takes the parameters utilityName, unitCost, and battery
 */
  public BatteryMeter(String utilityName, double unitCost, Battery battery) {
    super(utilityName, unitCost);
    this.battery = battery;
  }

/* Method report()
 * Overrides the default report() method in Meter
 * If the reading is positive and charge is available in the battery, takes charge from the battery
 * If the reading is negative, adds the charge to the battery
 * Prints out the name, reading, and battery charge
 * Stores and prints out the cost
 */
  public void report() {
    double batteryCharge = battery.getCharge();
    batteryCharge = Math.round(batteryCharge*100.0)/100.00;
    meterReading = Math.round(meterReading*100.0)/100;

    if((meterReading > 0.0) && (batteryCharge > 0.0)) {
      if(meterReading >= batteryCharge) {
        System.out.println("You have used " + batteryCharge + " units from the battery");
        meterReading -= batteryCharge;
        battery.subCharge(batteryCharge);
      }
      else if(batteryCharge > meterReading) {
        System.out.println("You have used " + meterReading + " units from the battery");
        battery.subCharge(meterReading);
        meterReading = 0;
      }
    }
    else if(meterReading < 0.0) {
      battery.addCharge(Math.abs(meterReading));
      System.out.println("You have generated " + Math.abs(meterReading) + " extra units");
      this.meterReading = 0;
    }
    
    System.out.println("Utility: " + utilityName);
    System.out.println("Reading: " + meterReading);
    System.out.println("Battery: " + battery.getCharge());
    double cost = meterReading * unitCost;
    cost = Math.round(cost*100.0)/100.00;
    this.finalCost = cost;
    System.out.println("Cost: " + "£" + (cost));
    System.out.println(" ");
  }

}
