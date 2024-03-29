import java.util.*;

/* Create the UserGroup class
 */
public class UserGroup {

/* Create an ArrayList called list
 */
  ArrayList<User> list = new ArrayList<User>();

/* Constructor for UserGroup
 */
  public UserGroup() {
  }

/* The method getUsers() returns listOfUsers
 */
  public ArrayList<User> getUsers() {
    return list;
  }

/* The method getUser() gets one element from the list
 * Takes an integer parameter as the index in the list
 */
  public User getUser(int index) {
    return list.get(index);
  }

/* Method printUsernames() prints each user's username and usertype
 * Uses an enhanced for loop
 */
  public void printUsernames() {
    for(User user : this.getUsers()) {
      System.out.println(user.getUsername() + " " + user.getUserType());
    }
  }

/* Method removeFirstUser() removes the user at index 0
 */
  public void removeFirstUser() {
    list.remove(list.get(0));
  }

/* Method removeLastUser() removes the last user
 */
  public void removeLastUser() {
    list.remove(list.size() - 1);
  }

/* Method removeUser() removes the selected user
 * Takes a string parameter
 * Uses an iterator to search for the username
 * Removes the user
 */
  public void removeUser(String usrname) {
      Iterator<User> iterUser = list.iterator();
      while(iterUser.hasNext()) {
        User next = iterUser.next();
        if(next.getUsername().equals(usrname)) {
        iterUser.remove();
        }
      }
  }

/* Method getUserIterator() creates an iterator called import junit.framework.TestCase;
 * Returns it
 */
  public Iterator<User> getUserIterator() {
    Iterator<User> it = list.iterator();
    return it;
  }

/* Method addSampleData() adds 10 new users to list
 * 8 are users and 2 are admins
 */
  public void addSampleData() {
    User user1 = new User("user1", "user", "emilia");
    list.add(user1);
    User user2 = new User("user2", "user", "caryl");
    list.add(user2);
    User user3 = new User("user3", "user", "jeremy");
    list.add(user3);
    User user4 = new User("user4", "user", "max");
    list.add(user4);
    User user5 = new User("user5", "user", "nathan");
    list.add(user5);
    User user6 = new User("user6", "user", "nina");
    list.add(user6);
    User user7 = new User("user7", "user", "ben");
    list.add(user7);
    User user8 = new User("user8", "user", "charlotte");
    list.add(user8);
    User user9 = new User("user9", "admin", "kirk");
    list.add(user9);
    User user10 = new User("user10", "admin", "pawel");
    list.add(user10);
  }

}
