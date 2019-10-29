/* Create the Calculator class
 */
 public class Calculator {

/* Initialise the instance variable x, this is the first operand
 */
	Double x;

/* Class constructor
 */
	public Calculator() {
	}

/* Method x()
 * Take the String parameter input, split the input on " " and sort it into the operands x and y, and the operator
 * Convert x and y to doubles
 * Set x to this.x
 * Check if the operator is + or x, and perform the appropriate action using y as the parameter
 * If the operator is not recognised, return null
 */
	public Double x(String input) {

    String[] array = input.split(" ");
		this.x = Double.parseDouble(array[0]);

		if(array[1].equals("+")) {
			Double y = Double.parseDouble(array[2]);
			return x(y);
		}
		else if(array[1].equals("x")) {
			double y = Double.parseDouble(array[2]);
			return x(y);
		}
		else {
			return null;
		}
	}

/* Add the parameter x to the instance variable x and return the result as a Double
 */
	public Double x(Double x) {
    if(x == 0.0) {
      this.x = 0.0;
    }
    else {
      System.out.println("== Adding ==");
  		this.x += x;
    }
    return this.x;
	}

/* Multiply the parameter x by the instance variable x and return the result as a Double
 */
	public Double x(double x) {

    if(x == 1.0) {
      this.x = 1.0;
    }
    else {
      System.out.println("== Multiplying ==");
  		this.x *= x;
    }
    return this.x;
	}

}
