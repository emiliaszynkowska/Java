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

  public Carnivore() {
    super();
  }

/* Abstract method makeNoise() is overidden by the subclasses
 */
  public abstract void makeNoise();

/* Method eat() makes the animal eat food
 * Carnivores can only eat meat
 * If the food is a plant an exception is thrown which has an error message
 */
  public void eat(Food food) throws Exception {
    if(food instanceof Plant) {
      throw new Exception("An error message");
    }
    else {
      System.out.println(this.getName() + " ate " + food.getName());
    }
  }

}
