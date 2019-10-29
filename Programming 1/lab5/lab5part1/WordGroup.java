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

}
