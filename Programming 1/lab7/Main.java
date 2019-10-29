/* Create the Main class
 */
public class Main {

/* Main method
 * Create a new Calculator called calculator
 * Create a new TestCalculator called test
 * Run testParser, this tests that the calculator can interpret inputs and perform calculations
 * Run testAdd, this tests that the calculator can add positive and negative numbers
 * Run testMultiplication, this tests that the calculator can multiply positive and negative numbers
 * Each method prints out statements to show the results of the test
 */
  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    TestCalculator test = new TestCalculator();

/* 1. testParser() method
 */
    System.out.println("Testing parsing..");
    boolean result1 = test.testParser(calculator);

/* 2. testAdd() method
 */
    System.out.println("Testing adding..");
    boolean result2 = test.testAdd(calculator);

/* 3. testMultiplication() method
 */
    System.out.println("Testing multiplication..");
    boolean result3 = test.testMultiplication(calculator);

/* 4. Final result
 * Use the results from testParser(), testAdd() and testMultiplication() to decide if the calculator is working
 * Print a statement to show the result of the test
 */
    if((result1 == true) && (result2 == true) && (result3 == true)) {
      System.out.println("Congratulations, your Calculator appears to be working.");
    }
    else {
      System.out.println("Your calculator is not working correctly.");
    }
  }

}
