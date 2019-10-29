import java.util.*;

/* Create the Main class
 */
public class Main {

/* Main method
 * Create a new UserGroup called users
 * Create a new UserGroup called administrators
 * Add 10 sample users
 * Print the username and userType for each user
 * Add any admins in users to administrators
 * Print the username and userType for each admin
 */
  public static void main(String[] args) {
    UserGroup users = new UserGroup();
    UserGroup administrators = new UserGroup();
    users.addSampleData();
    users.printUsernames();
    Iterator<User> iterator = users.getUserIterator();
    User next;
    while(iterator.hasNext()) {
      next = iterator.next();
      if(next.getUserType() == "admin") {
        administrators.getUsers().add(next);
      }
    }
    administrators.printUsernames();
  }
}
