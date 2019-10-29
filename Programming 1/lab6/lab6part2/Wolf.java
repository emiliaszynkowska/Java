/* Create the Wolf class
 * It is a subclass of Animal and Carnivore
 */
public class Wolf extends Carnivore {

/* Initialise the instance variable howl
 */
  public String howl = "howl!";

/* Constructor takes string and integer parameters
 * Sets a and b to name and age
 * The super keyword references the method from the superclass
 */
  public Wolf(String a, Integer b) {
    super(a, b);
  }

/* Method makeNoise() overrides the abstract method makeNoise() in the superclass
 * Prints out howl
 */
  public void makeNoise() {
    System.out.println(howl);
  }

}
