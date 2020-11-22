package driver;

/**
 * A StdOut is a stream that a command that produces output uses to send output
 * they produce to their destinations.
 */
public class StdOut {

	/**
	 * An integer representing the type of destination: 0 represents the command
	 * line, 1 represents overwriting a file, and 2 represents appending to a
	 * file.
	 */
	private int destinationType;
	/**
	 * If destinationType is 1 or 2, this is the File that is to be
	 * overwritten/appended to. Otherwise, it is null and we know the actual
	 * destination is shell's command line.
	 */
	private File destination;
	/** The JShell of the destination */
	private JShell shell;

	/**
	 * Initializes an instance of a StdOut
	 * 
	 * @param shell
	 *            The JShell of the destination of the StdOut
	 * @param type
	 *            An integer representing the type of destination
	 * @param destination
	 *            If type is 1 or 2, this is the File that is to be
	 *            overwritten/appended to. Otherwise, it's null.
	 */
	public StdOut(JShell shell, int type, File destination) {
		this.shell = shell;
		this.destinationType = type;
		this.destination = destination;
		if (destinationType == 1) {
			// If it's meant to be overwritten, empty it out first
			destination.overwrite("");
		}
	}

	/**
	 * Sends a given message to this StdOut's destination as a line.
	 * 
	 * @param message
	 *            The String to be sent to the destination of the StdOut
	 */
	public void sendLine(String message) {
		if (this.destinationType == 0) {
			this.shell.println(message);
		} else {
			this.destination.append(message + "\n");
		}
	}

	/**
	 * Sends a given message to this StdOut's destination.
	 * 
	 * @param message
	 *            The String to be sent to the destination of the StdOut
	 */
	public void send(String message) {
		if (this.destinationType == 0) {
			this.shell.print(message);
		} else {
			this.destination.append(message);
		}
	}
}
