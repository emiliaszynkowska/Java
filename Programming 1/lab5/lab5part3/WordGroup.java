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

/* Method getWordCounts() creates a HashMap of all the words and how many times they are repeated
 * Create a HashMap called wordMap
 * If the word is not already in the map it is added with a value of 1
 * Otherwise the value is increased by 1
 */
  public HashMap<String, Integer> getWordCounts() {
    HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
    for(String word : this.getWordArray()) {
      if(wordMap.containsKey(word)) {
          int count = wordMap.get(word);
          count ++;
          wordMap.put(word, count);
        }
      else {
          wordMap.put(word, 1);
      }
    }
    return wordMap;
  }

}
