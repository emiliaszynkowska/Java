/* Create the FlashCard class
 */
public class FlashCard {

/* Set the instance variables 
 */
    private String question;
    private String answer;
    private String userAnswer;
    private String rightWrong;

/* Constructor for FlashCard
 * Takes the parameters question and answer
 * Set these to the instance variables
 */
    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

/* Getters and setters
 */ 
    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUserAnswer() {
        return this.userAnswer;
    }

    public String getRightWrong() {
        return this.rightWrong;
    }

    public void setRightWrong(String rightWrong) {
        this.rightWrong = rightWrong;
    }

}