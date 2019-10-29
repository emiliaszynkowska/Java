/* Create the Plant class
 * It is a subclass of Food
 */
public class Plant extends Food {

/* Constructor takes a string parameter
 * Sets a to name
 * The super keyword references the constructor from the superclass
 */
  public Plant(String a) {
    super(a);
    name = a;
  }

}
