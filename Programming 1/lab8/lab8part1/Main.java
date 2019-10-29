/* Create the Main class
 */
public class Main {

/* Create a new Wolf called wolf1
 * Create a new Parrot called parrot1
 * Create a new Meat called meat1
 * Create a new Plant called plant1
 * Call makeNoise() on wolf1
 * Call makeNoise() on parrot1
 * Try eat() on wolf1
 * If wolf 1 tries to eat a plant, catch the exception
 * Print an error message
 * This is called a try-catch block
 */
  public static void main(String[] args) {
    Carnivore wolf1 = new Wolf("Wolfy", 2);
    Carnivore wolf2 = new Wolf();
    Omnivore parrot1 = new Parrot("Parroty", 3);
    Food meat1 = new Meat("Chicken");
    Food plant1 = new Plant("Kale");
    wolf1.makeNoise();
    parrot1.makeNoise();
    wolf1.eat(meat1, 3);
  }

}
