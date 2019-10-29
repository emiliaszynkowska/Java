/* Create the Animal class
 * This class is abstract, this means it contains abstract methods
 * An abstract class cannot be instantiated, and requires subclasses
 * Methods in the subclasses provide implementations for the abstract methods
 */
public abstract class Animal {

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

}
