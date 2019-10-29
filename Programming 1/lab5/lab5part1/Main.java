/* Create the Main class
 */
public class Main {

/* Main method
 * Create 2 new wordgroups group1 and group2
 * Get an array for each group
 * Print out the arrays
 */
  public static void main(String[] args) {
    WordGroup group1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
    WordGroup group2 = new WordGroup("When you play play hard when you work dont play at all");
    String[] array1 = group1.getWordArray();
    String[] array2 = group2.getWordArray();
    for(String string : array1) {
      System.out.println(string);
    }
    for(String string : array2) {
      System.out.println(string);
    }
  }

}
