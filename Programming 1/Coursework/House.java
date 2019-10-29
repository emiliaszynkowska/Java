import java.util.*;
import java.io.*;

/* House class
 */
public class House {

/* Instance variables:
 * meters - a list of all the meters in the house
 * appliances - a list of all the appliances in the house
 * waterMeter, battery and batteryMeter - these measure the use of water and electricity
 * finalCost - this is used in the activate method
 */
  ArrayList<Meter> meters = new ArrayList<Meter>();
  ArrayList<Appliance> appliances = new ArrayList<Appliance>();
  Meter waterMeter = new Meter("Water Meter", 0.002);
  Battery battery = new Battery();
  BatteryMeter batteryMeter = new BatteryMeter("Battery Meter", 0.013, battery);
  double finalCost = 0.0;

/* Constructor
 * Adds the battery meter and water meter to the house
 */
  public House(String filename) {
    meters.add(batteryMeter);
    meters.add(waterMeter);
  }

/* Method addElectricAppliance()
 * Adds the appliance to the ArrayList of appliances
 * Connects the appliance to the electric meter and sets its type to electric
 * This means that the appliance will use electricity from the electric meter 
 */
  public void addElectricAppliance(Appliance appliance) {
    appliances.add(appliance);
    appliance.setMeter(batteryMeter);
    appliance.setType("electric");
  }

/* Method addWaterAppliance()
 * Adds the appliance to the ArrayList of appliances
 * Connects the appliance to the water meter and sets its type to water
 * This means that the appliance will use water from the water meter 
 */
  public void addWaterAppliance(Appliance appliance) {
    appliances.add(appliance);
    appliance.setMeter(waterMeter);
    appliance.setType("water");
  }

/* Method removeAppliance()
 * Removes the appliance from the ArrayList of appliances
 */
  public void removeAppliance(Appliance appliance) {
    appliances.remove(appliance);
  }

/* Integer numAppliances()
 * Returns the number of appliances in the house
 */
  public int numAppliances() {
    return appliances.size();
  }

/* Method activate()
 * This is the default method
 * Runs the house for 7 days
 * Gives the battery a starting charge of 1000 units
 * Runs each appliance, consumes units from the appropriate meter
 * Reports for each meter
 * See Meter and BatteryMeter for details about the report() method
 */
  public void activate() {
    finalCost = 0.0;
    System.out.println("\n" + "Activating house..");
    System.out.println("Simulating for 7 days");
    battery.setCharge(1000.0);
    for(Appliance appliance : appliances) {
      appliance.timePasses(168);
      if(appliance.getType().equals("electric")) {
        batteryMeter.consumeUnits(appliance.tellMeterToConsumeUnits());
      }
      if(appliance.getType().equals("water")) {
        waterMeter.consumeUnits(appliance.tellMeterToConsumeUnits());
      }
    }
    batteryMeter.report();
    waterMeter.report();
    finalCost += batteryMeter.getCost();
    finalCost += waterMeter.getCost();
    finalCost = Math.round(finalCost*100.0)/100.00;
    System.out.println("Final cost: £" + finalCost + "\n");
  }

/* Method activate(int time)
 * This overrides the default method
 * Runs the house for a specified number of hours
 * Gives the battery a starting charge of 1000 units
 * Runs each appliance, consumes units from the appropriate meter
 * Uses a for loop to run timePasses with an argument of 1 separately, many times
 * Reports for each meter after every hour
 * Finally, prints the total cost 
 */
  public void activate(int time) {
    System.out.println("\n" + "Activating house..");
    System.out.println("Running for " + time + " hour(s)");
    battery.setCharge(1000.0);
    finalCost = 0.0;
    for(int i = 1; i <= time; i ++) {
      for(Appliance appliance : appliances) {
        appliance.timePasses(1);
        if(appliance.getType().equals("electric")) {
          batteryMeter.consumeUnits(appliance.tellMeterToConsumeUnits());
        }
        if(appliance.getType().equals("water")) {
          waterMeter.consumeUnits(appliance.tellMeterToConsumeUnits());
        }
      }
      System.out.println("Time passed: " + i + " hours");
      batteryMeter.report();
      waterMeter.report();
      finalCost += batteryMeter.getCost();
      finalCost += waterMeter.getCost();
    }
    finalCost = Math.round(finalCost*100.0)/100.00;
    System.out.println("Final cost: £" + finalCost);
  }

}
