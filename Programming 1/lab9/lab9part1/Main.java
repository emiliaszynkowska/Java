/* Create the Main class
 */
public class Main {

    public static void main(String[] args) {
/* Create a new FlashCardReader
 * Check if the file is ready to be ready
 * Print a line of the file
 */
        FlashCardReader flashCardReader = new FlashCardReader("Questions.txt");
        System.out.println("Status of file: " + flashCardReader.fileIsReady());
        System.out.println(flashCardReader.getLine());
    }
    
}