/* Create the Carnivore class
 * It is a subclass of Animal and superclass of Wolf
 */
public abstract class Carnivore extends Animal {

/* Constructor takes string and integer parameters
 * Sets a and b to name and age
 * The super keyword references the constructor from the superclass
 */
  public Carnivore(String a, Integer b) {
    super(a, b);
    name = a;
    age = b;
  }

/* Abstract method makeNoise() is overidden by the subclasses
 */
  public abstract void makeNoise();

}
