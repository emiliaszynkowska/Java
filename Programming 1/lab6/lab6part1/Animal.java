/* Create the Animal class
 */
public class Animal {

/* Initialise the instance variables name and age
 */
  private String name;
  private int age;

/* The constructor takes string and integer parameters
 * These are set to name and age
 */
  public Animal(String a, int b) {
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
  public int getAge() {
    return this.age;
  }

}
