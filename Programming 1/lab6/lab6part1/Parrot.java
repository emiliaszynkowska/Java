/* Create the Parrot class
 * It is a subclass of Animal
 */
public class Parrot extends Animal {

/* Constructor takes string and integer parameters
 * Sets a and b to name and age
 * The super keyword references the constructor from the superclass
 */
  public Parrot(String a, int b) {
    super(a, b);
  }

/* Method makeNoise() overrides the abstract method makeNoise() in the superclass
 * Prints out chirp
 */
  public void makeNoise() {
    System.out.println("chirp");
  }

}
