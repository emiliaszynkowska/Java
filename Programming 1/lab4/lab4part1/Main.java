/* Create the Main class
 */
public class Main {

/* Main method
 *
 * Part 1 : Times Tables
 * Create a new instance of Toolbox
 * Read an integer 'number' from command line
 * Use a for loop to multiply 'number' by integers up to 20
 * This prints a times table of 'number'
 */
  public static void main(String[] args) {
    System.out.println("Welcome to times tables");
    Toolbox myToolbox = new Toolbox();
    Integer number = myToolbox.readIntegerFromCmd();
    for(int x = 1; x < 21; x ++) {
      int result = x * number;
      System.out.println(number + " x " + x + " = " + result);
    }

/* Part 2 : Successive integers to 500
 */
    System.out.println("How many successive integers add to make 500?");

/* Initialise the variables 'total' and 'x' outside the while loop
 */
    int total = 0;
    int x = 1;

/* Create a while loop
 * Adds x to the total each time, then increases x by 1
 * Stops when total reaches 500
 * There are always 33 iterations
 */
    while(total <= 500) { {
        System.out.println(total + " + " + x + " = " + (total += x));
        x += 1;
      }
    }
    System.out.println("There were " + x + " iterations");
    }

}
