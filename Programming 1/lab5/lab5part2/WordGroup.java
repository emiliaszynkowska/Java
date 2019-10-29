import java.util.*;

/* Create the WordGroup class
 */
public class WordGroup {

/* Initialise the instance variable 'words' which is an empty string
 */
  String words = "";

/* Method WordGroup() converts the wordgroup to lower case
 * Takes the string parameter lower
 */
  public WordGroup(String lower) {
    lower = lower.toLowerCase();
    words += lower;
  }

/* Method getWordArray() splits words on spaces and adds them to an array
 * Returns the array
 */
  public String[] getWordArray() {
    String[] array = words.split(" ");
    return array;
  }

/* Method getWordSet() creates a HashSet of strings
 * Takes a WordGroup parameter grp
 * Uses an enhanced for loop to add strings to wordSet
 * Returns wordSet
 */
  public HashSet<String> getWordSet(WordGroup grp) {
    HashSet<String> wordSet = new HashSet<String>();
    for(String word : this.getWordArray()) {
      wordSet.add(word);
    }
    for(String word : grp.getWordArray()) {
      wordSet.add(word);
    }
    return wordSet;
  }

}
