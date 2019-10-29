/* Create the TestCalculator class
 */
public class TestCalculator {

/* Class constructor
 */
  public TestCalculator() {
  }

/* Test that 12+5 return the double 17
 * Test that 12x5 returns the double 60
 * Test that 12[3 returns null
 * Print a statement to show if the test has passed or failed
 * Decide if all three subtests have passed or failed
 */
  public boolean testParser(Calculator calculator) {

/* 1 - Calculate the test results using the Calculator class
 */
    Double part1test1 = calculator.x("12 + 5");
    Double part1test2 = calculator.x("12 x 5");
    Double part1test3 = calculator.x("12 [ 3");

/* 2 - Check if the test results are correct
 */
    if((part1test1 instanceof Double) && (part1test1 == 17)) {
      System.out.println("[PASS] Adding works correctly");
    }
    else {
      System.out.println("[FAIL] Adding does not work correctly");
    }
    if((part1test2 instanceof Double) && (part1test2 == 60)) {
      System.out.println("[PASS] Multiplication works correctly");
    }
    else {
      System.out.println("[FAIL] Multiplication does not work correctly");
    }
    if(part1test3 == null) {
      System.out.println("[PASS] Parser returns null for unsupported operators");
    }
    else {
      System.out.println("[FAIL] Parser does not return null for unsupported operators");
    }

/* 3 - Decide whether all three tests have passed or failed
 */
    if((part1test1 instanceof Double) && (part1test1 == 17) && (part1test2 instanceof Double) && (part1test2 == 60) && (part1test3 == null)) {
      return true;
    }
    else {
      return false;
    }
  }

/**
 * Test that positive numbers can be added
 * Test that negative numbers can be added
 * Print a statement to show if the test has passed or failed
 * Decide if both subtests have passed or failed
 */
  public boolean testAdd(Calculator calculator) {

/* 1 - Calculate the test results using the Calculator class
 */
    calculator.x = 0.0;
    Double operand1 = 2.0;
    Double operand2 = 2.0;
    calculator.x(operand1);
    Double part2test1 = calculator.x(operand2);

    calculator.x = 0.0;
    Double op1 = -2.0;
    Double op2 = -6.0;
    calculator.x(op1);
    Double part2test2 = calculator.x(op2);

/* 2 - Check if the test results are correct
 */
    if(part2test1 == 4.0) {
      System.out.println("[PASS] Positive numbers are added correctly");
    }
    else {
      System.out.println("[FAIL] Positive numbers are not added correctly");
    }
    if(part2test2 == -8.0) {
      System.out.println("[PASS] Negative numbers are added correctly");
    }
    else {
      System.out.println("[FAIL] Negative numbers are not added correctly");
    }

/* 3 - Decide whether both tests have passed or failed
 */
    if((part2test1 == 4.0) && (part2test2 == -8.0)) {
      return true;
    }
    else {
      return false;
    }
  }

/**
 * Test can positive numbers can be multiplied
 * Test that negative numbers can be multiplied
 * Print a statement to show if the test has passed or failed
 * Decide if both subtests have passed or failed
 */
  public boolean testMultiplication(Calculator calculator) {

/* 1 - Calculate the test results using the Calculator class
 */
    calculator.x = 1.0;
    double oper1 = 2.0;
    double oper2 = 2.0;
    calculator.x(oper1);
    Double part3test1 = calculator.x(oper2);

    calculator.x = 1.0;
    double oper3 = -2.0;
    double oper4 = -2.0;
    calculator.x(oper3);
    Double part3test2 = calculator.x(oper4);

/* 2 - Check if the test results are correct
 */
    if(part3test1 == 4.0) {
      System.out.println("[PASS] Positive numbers are multiplied correctly");
    }
    else {
      System.out.println("[FAIL] Positive numbers are not multiplied correctly");
    }
    if(part3test2 == 4.0) {
      System.out.println("[PASS] Negative numbers are multiplied correctly");
    }
    else {
      System.out.println("[FAIL] Negative numbers are not multiplied correctly");
    }

/* 3 - Decide whether both tests have passed or failed
 */
    if((part3test1 == 4.0) && (part3test2 == 4.0)) {
      return true;
    }
    else {
      return false;
    }
  }

}
