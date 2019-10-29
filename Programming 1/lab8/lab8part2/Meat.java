/* Create the Meat class
 * It is a subclass of Food
 */
 public class Meat extends Food {

/* Constructor which takes a string parameter
 * Sets the string to name
 * The super keyword is used to reference the constructor from the superclass
 */
  public Meat(String a) {
    super(a);
    name = a;
  }

}
