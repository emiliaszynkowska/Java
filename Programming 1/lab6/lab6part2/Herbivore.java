/* Create the Herbivore class
 * It is a subclass of Animal
 */
public class Herbivore extends Animal {

/* Constructor takes string and integer parameters
 * Sets a and b to name and age
 * The super keyword references the constructor from the superclass
 */
  public Herbivore(String a, Integer b) {
    super(a, b);
    name = a;
    age = b;
  }

/* Abstract method makeNoise() is overidden by the subclasses
 */
  public abstract void makeNoise();

/* Method eat() makes the animal eat food
 */
  public void eat(Food food) {
  }

}
