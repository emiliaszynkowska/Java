/* Create the Parrot class
 * It is a subclass of Animal and Omnivore
 */
public class Parrot extends Omnivore {

/* Initialise the instance variable chirp
 */
  String noise = "chirp!";

/* Constructor takes string and integer parameters
 * Sets a and b to name and age
 * The super keyword references the constructor from the superclass
 */
  public Parrot(String a, Integer b) {
    super(a, b);
    name = a;
    age = b;
  }

/* Method makeNoise() overrides the abstract method makeNoise() in the superclass
 * Prints out chirp
 */
  public void makeNoise() {
    System.out.println(noise);
  }

}
