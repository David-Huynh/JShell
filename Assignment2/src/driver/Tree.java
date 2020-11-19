package driver;

/**
 * The Tree command displays the entire file system as a tree on the shell.
 */
public class Tree extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 *
	 * @return The manual
	 */
	public static String getManual() {
		return "tree";
	}

	/**
	 * Tell the shell to print to its command line the entire file system as a
	 * tree.
	 * 
	 * @param shell
	 *            The JShell the command is to be performed on
	 * @param parameters
	 *            The parameters from the interpreter the command is to work
	 *            with
	 */
	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length != 1) {
			PrintError.reportError(shell, "man",
					"Invalid number of arguments.");
			return;
		}
	}

}
