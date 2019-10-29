import java.util.*;

/* Create the User Class
 */
public class User {

/* Initialise the instance variables username, userType and name
 */
    String username;
    String userType;
    String name;

/* Create the User constructor
 * Takes the parameters a, b and c
 * a, b and c are assigned to the variables username, userType and name
 * The keyword 'this' is used to reference the current instance
 */
    public User(String a, String b, String c) {
      this.username = a;
      this.userType = b;
      this.name = c;
    }

/* Create the method getUsername()
 * Returns username
 */
    public String getUsername() {
      return this.username;
    }

/* Create the method getUserType()
 * Returns userType
 */
    public String getUserType() {
      return this.userType;
    }

/* Create the method getName()
 * Returns name
 */
    public String getName() {
      return this.name;
    }

/* The method setUserType takes a String parameter and allows the user to change the userType
 * userType should be 'user', 'admin' or 'editor'
 */
    public void setUserType(String type) {
      this.userType = type;
    }
}
