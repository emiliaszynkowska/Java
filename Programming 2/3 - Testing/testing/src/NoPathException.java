/**
 * A checked exception stating that there is no path between two nodes in a
 * graph.
 * @author htson
 */
 
public class NoPathException extends Exception {

	/**
	 * The default constructor.
	 */
	public NoPathException() {
        super();
    }

	/**
	 * Contruct an exception with a particular error message.
	 */
    public NoPathException(String msg) {
        super(msg);
    }
}