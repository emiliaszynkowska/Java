import java.util.*;
import java.io.*;

/* Create the Quiz class
 */
public class Quiz {

/* Initialise instance variables
 * Create the FlashCardReader, ArrayList, Toolbox, and PrintStream
 */
    private String saved;
    private FlashCardReader flashCardReader;
    private ArrayList<FlashCard> flashcards;
    private Toolbox toolbox = new Toolbox();

/* Constructor for Quiz
 * Takes the String parameter filename and reads questions from that file
 * Get the ArrayList of FlashCards with questions and answers
 * Play the quiz
 */
    public Quiz(String filename) {
        this.flashCardReader = new FlashCardReader(filename);
        flashcards = this.flashCardReader.getFlashCards();
        play();
    }

/* Method play() asks questions to the user
 * Check if the answers should be saved, if so create a new save file
 * Check if the file is ready to be read
 * Print out each question and take a String input from the user
 * Store the input as userAnswer
 * Print if it was correct or not, print out the correct answer if it was wrong
 */
    public void play() {

        if(flashCardReader.fileIsReady() == true) {
            for(FlashCard flashcard : flashcards) {
                System.out.println(flashcard.getQuestion());
                String userAnswer = toolbox.readStringFromCmd();
                flashcard.setUserAnswer(userAnswer);
                if(flashcard.getAnswer().equals(userAnswer)) {
                    System.out.println("correct");
                    flashcard.setRightWrong("right");
                } else {
                    System.out.println("wrong");
                    System.out.println("the correct answer was " + flashcard.getAnswer());
                    flashcard.setRightWrong("wrong");
                }
            }
        }
    }

}