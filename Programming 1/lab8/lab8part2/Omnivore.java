/* Create the Omnivore class
 * It is a subclass of Animal and superclass of Parrot
 */
public abstract class Omnivore extends Animal {

/* Constructor takes string and integer parameters
 * Sets a and b to name and age
 * The super keyword references the constructor from the superclass
 */
  public Omnivore(String a, Integer b) {
    super(a, b);
  }

  public Omnivore(Integer b) {
    super(b);
  }

  public Omnivore() {
    super();
  }

/* Abstract method makeNoise() is overidden by the subclasses
 */
  public abstract void makeNoise();

/* Method eat() makes the animal eat food
 */
  public void eat(Food food) {
  }

}
