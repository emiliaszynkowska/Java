/* Create the Animal class
 * This class is abstract, this means it contains abstract methods
 * An abstract class cannot be instantiated, and requires subclasses
 * Methods in the subclasses provide implementations for the abstract methods
 */
public abstract class Animal implements Comparable<Animal> {

/* Initialise the instance variables name and age
 */
  public String name;
  public Integer age;

/* The constructor takes string and integer parameters
 * These are set to name and age
 */
  public Animal(String a, Integer b) {
    name = a;
    age = b;
  }

  public Animal(Integer b) {
    age = b;
  }

  public Animal() {
    this("newborn", 0);
  }

  public int compareTo(Animal animal) {
    if(animal.getAge() < this.getAge()) {
      return 1;
    }
    else if(animal.getAge() > this.getAge()) {
      return -1;
    }
    return 0;

  }

/* Method getName() returns the name of the current instance
 */
  public String getName() {
    return this.name;
  }

/* Method getAge() returns the age of the current instance
 */
  public Integer getAge() {
    return this.age;
  }

/* Method makeNoise() is abstract
 * It is overidden by the makeNoise() method in the subclasses
 */
  public abstract void makeNoise();

/* Abstract method eat() makes the Animal eat food
 * It takes a Food parameter
 * Can throw an exception - see subclasses
 */
  public abstract void eat(Food food) throws Exception;

  public void eat(Food food, Integer it) {
    for(int i = 0; i < it; i++) {
      try {
          this.eat(food);
      }
      catch(Exception e) {
        System.err.println(e);
      }
    }
  }

}
