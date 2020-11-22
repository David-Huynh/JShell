package driver;

public class ClientURL extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "curl URL\n"
				+ "Retrieve the file at that URL and add it to the current "
				+ "working directory.\n" + "Example1:\n"
				+ "curl http://www.cs.cmu.edu/ spok/grimmtmp/073.txt\n"
				+ "Will get the contents of the file, "
				+ "i.e. 073.txt and create a "
				+ "file called 073.txt with the contents in \nthe current "
				+ "working directory.\n" + "Example2:\n"
				+ "curl http://www.ub.edu/gilcub/SIMPLE/simple.html\n"
				+ "Will get the contents of the file, i.e. simple.html "
				+ "(raw HTML) and create a file called simple.html with the\n"
				+ "contents in the current working directory.";
	}

	/**
	 * Perform the outcome of this command
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
	}

}
