import java.io.*;

/* Create the FlashCardReader class
 */
public class FlashCardReader {

/* Initialise the instance variables reader and line
 */
    BufferedReader reader;
    String line;

/* Constructor for FlashCardReader
 * Takes a String parameter for the name of the file
 * Create a new BufferedReader for this file
 * Throw a FileNotFoundException if there is an error
 */
    public FlashCardReader(String filename) {
        try {
            reader = new BufferedReader(new FileReader(filename));
            System.out.println("File found successfully");
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage() + " The file was not found");
        }
    }

/* Method getLine() reads one line of the file
 * Throw an IO Exception if there is an error
 */
    public String getLine() {
        try {
            line = reader.readLine();
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage() + " Error reading file");
        }
        return line;
    }

/* Checks if the file is ready to be read
 * Use the .ready() command to do this
 * Throw an IO Exception if there is an error
 */
    public Boolean fileIsReady() {
        try {
            if(reader.ready() == true) {
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage() + " Error reading file");
            return false;
        }
        return true;
    }

}