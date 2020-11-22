package driver;

/**
 * The Search command is used by the user to search for files/directories in the
 * file system.
 */

public class Search extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 *
	 * @return The manual
	 */
	public static String getManual() {
		return "search path ... -type [f|d] -name expression\n"
				+ "This command takes in at least three arguments. "
				+ "Searches for files/directories\nas specified after "
				+ "-type that has the name expression which is specified "
				+ "after -name.\nHowever, if path or [f|d] or "
				+ "expression is not specified, then give back an error";
	}

	/**
	 * Tell the JShell to make two new directories according to the user's
	 * specifications.
	 *
	 * @param shell
	 *            The JShell the command is to be performed on
	 * @param parameters
	 *            The parameters from the interpreter the command is to work
	 *            with
	 * @param outputType
	 *            An integer representing the type of destination: 0 represents
	 *            the command line, 1 represents overwriting a file, and 2
	 *            represents appending to a file
	 * @param outputFile
	 *            If outputType is 1 or 2, this is the file we are
	 *            overwriting/appending to, otherwise null
	 */
	public static void performOutcome(JShell shell, String[] parameters,
			int outputType, File outputFile) {
		StdOut stdout = new StdOut(shell, outputType, outputFile);

	}
}
