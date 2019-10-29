import java.util.*;

/* Create the Main class
 */
public class Main {

/* Main method
 * Create 2 new wordgroups group1 and group2
 * Create a HashSet from the groups
 * Print the HashSet
 */
  public static void main(String[] args) {
    WordGroup group1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
    WordGroup group2 = new WordGroup("When you play play hard when you work dont play at all");
    for(String string : group1.getWordSet(group2)) {
      System.out.println(string);
    }
  }

}
