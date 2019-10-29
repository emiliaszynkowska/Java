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
    private PrintStream saveResults;

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
        System.out.println("Would you like to save your answers?");
        this.saved = toolbox.readStringFromCmd();
        if(saved.equals("Y")) {
            System.out.println("Your answers will be saved");
            try {
            	File save = new File("save.txt");
                if(save.exists()) {
                    save.delete();
                    System.out.println("File deleted");
                }
                saveResults = new PrintStream(new FileOutputStream("save.txt"));
            }
            catch(Exception e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        } else if(saved.equals("N")) {
            System.out.println("Your answers will not be saved");
        } else {
            System.out.println("Error");
        }
        if(flashCardReader.fileIsReady()) {
            int score = 0;
            for(FlashCard flashcard : flashcards) {
                System.out.println(flashcard.getQuestion());
                String userAnswer = toolbox.readStringFromCmd();
                flashcard.setUserAnswer(userAnswer);
                if(flashcard.getAnswer().equals(userAnswer)) {
                    System.out.println("correct");
                    score ++;
                    flashcard.setRightWrong("right");
                } else {
                    System.out.println("wrong");
                    System.out.println("the correct answer was " + flashcard.getAnswer());
                    flashcard.setRightWrong("wrong");
                }
                if(saveResults != null) {
                	save(flashcard.getQuestion(), userAnswer, flashcard.getRightWrong());
                }
            }
            if(saveResults != null) {
            	save(score, flashcards.size(), (score * 100)/flashcards.size());
            }
        }
    }

/* Method save() adds the question, answer, and result to the save file
 */   
    public void save(String question, String userAnswer, String rightWrong) {
    	saveResults.append(question + "," + userAnswer + "," + rightWrong + "\n");
    }

/* Overloaded method save() adds the score, number of questions and percentage to the save file
 */   
    public void save(int score, int questions, double percentage) {
    	saveResults.append(score + "," + questions + "," + percentage + "\n");
    }
    
}