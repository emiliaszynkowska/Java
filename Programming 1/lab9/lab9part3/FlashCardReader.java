import java.io.*;
import java.util.*;

/* Create the FlashCardReader class
 */
public class FlashCardReader {

/* Initialise the instance variables reader and line
 * Create an ArrayList of FlashCards called flashcards
 */
    BufferedReader reader;
    String line;
    ArrayList<FlashCard> flashcards = new ArrayList<FlashCard>();

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
            System.exit(0);
        }
    }

/* Method getLine() reads one line of the file
 * Throw an IO Exception if there is an error
 */
    public String getLine() {
        try {
            return reader.readLine();
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage() + " Error reading file");
        }
        return " ";
    }

/* Checks if the file is ready to be read
 * Use the .ready() command to do this
 * Throw an IO Exception if there is an error
 */
    public Boolean fileIsReady() {
        try {
            if(reader.ready()) {
                String line = reader.readLine();
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage() + " Error reading file");
            return false;
        }
        return true;
    }

/* Add data for the FlashCards
 * Get the current line of the file and split the question and answer at ":"
 * Store the question and the answer in FlashCard
 * Add the FlashCard to the ArrayList flashcards
 * Return flashcards
 */
    public ArrayList<FlashCard> getFlashCards() {
        String currentLine = getLine();
        String[] currentLineString;
        while(currentLine != null) {
            currentLineString = currentLine.split(":");
            String question = currentLineString[0];
            String answer = currentLineString[1];
            FlashCard flashcard = new FlashCard(question, answer);
            flashcards.add(flashcard);
            currentLine = getLine();
        }
        return flashcards;
    }

}