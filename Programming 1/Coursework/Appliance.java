import java.util.*;

/* Appliance class
 * Appliance is abstract and cannot be instantiated
 * Appliance provides shared methods and variables which all the subclasses use
 * This reduces code duplication and simplifies code
 */
public abstract class Appliance {

/* Instance variables:
 * name - name of the appliance
 * meter - batteryMeter or waterMeter
 * type - electric or water
 */
  private String name;
  private Meter meter;
  private String type;

/* Constructor
 */
  public Appliance() {
  }

/* Getters and setters
 */
  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setMeter(Meter meter) {
    this.meter = meter;
  }

  public Meter getMeter() {
    return this.meter;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

/* Abstract method timePasses()
 * Abstract double tellMeterToConsumeUnits()
 * These methods are instantiated in the subclasses
 */
  public abstract void timePasses(int time);
  protected abstract double tellMeterToConsumeUnits();

}
