import java.util.*;

/* Create the Main class
 */
public class Main {

/* Main method
 * Create a new UserGroup called group1
 * Add 10 sample users
 * Print the username and userType for each user
 */
  public static void main(String[] args) {
    UserGroup group1 = new UserGroup();
    group1.addSampleData();
    group1.printUsernames();
  }

}
