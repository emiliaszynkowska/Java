import java.util.*;

/* Create the Main class
 */
public class Main {

/* Main method
 */
  public static void main(String[] args) {
/* Create 2 new wordgroups group1 and group2
 * Create a HashSet from the groups
 */
    WordGroup group1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
    WordGroup group2 = new WordGroup("When you play play hard when you work dont play at all");
    for(String string : group1.getWordSet(group2)) {
      System.out.println(string);
    }

/* Create a HashMap from group1
 * The 'key' is the word and the 'value' is the number of times it is repeated
 * Use an iterator to print the key and value
 */
    HashMap<String, Integer> map1 = group1.getWordCounts();
    Iterator iterator1 = map1.keySet().iterator();
    while (iterator1.hasNext()) {
      String key = iterator1.next().toString();
      Integer value = map1.get(key);
      System.out.println(key + ": " + value);
    }
/* Create a HashMap from group2
 * The 'key' is the word and the 'value' is the number of times it is repeated
 * Use an iterator to print the key and value
 */
    HashMap<String, Integer> map2 = group2.getWordCounts();
    Iterator iterator2 = map2.keySet().iterator();
    while (iterator2.hasNext()) {
      String key = iterator2.next().toString();
      Integer value = map2.get(key);
      System.out.println(key + ": " + value);
    }

  }

}
