// **********************************************************
// Assignment2:
// Student1: Collin Chan
// UTORID user_name: chancol7
// UT Student #: 1006200889
// Author: Collin Chan
//
// Student2: Jeff He
// UTORID user_name: Hejeff2
// UT Student #: 1006398783
// Author: Jeff He
//
// Student3: Nevin Wong
// UTORID user_name: wongnevi
// UT Student #: 1005391434
// Author: Nevin Wong
//
// Student4: David Huynh
// UTORID user_name: huynhd12
// UT Student #: 1005991937
// Author: David Huynh
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package driver;

import java.lang.reflect.Array;
import java.util.*;

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
		boolean hasError = hasErrors(shell, parameters);
		if (hasError) {
			return;
		}
		// check for paths recursively


	}


	/**
	 * Checks if parameters are valid for function call
	 *
	 * @param shell
	 *						The JShell the command is to be performed on
	 * @param parameters
	 * 						The parameters from the interpreter the command is to work
	 * 						with
	 * @return a boolean value that signifies if there is an error.
	 */
	private static boolean hasErrors(JShell shell, String [] parameters) {
		if (parameters.length < 6) {
			PrintError.reportError(shell, "search",
					"This command does not produce stdout.");
			return true;
		}
		int tIndex = 0;
		int nIndex = 0;
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i].equals("-type")) {
				tIndex = i;
			} else if (parameters[i].equals("-name")) {
				nIndex = i;
			}
		}
		if (tIndex == 0 || nIndex == 0) {
			PrintError.reportError(shell, "search",
					"-type and -name are required parameters.");
			return true;
		}
		if (tIndex < nIndex) {
			PrintError.reportError(shell, "search",
					"-type should come before -name.");
			return true;
		}
		if (nIndex+1 == parameters.length-1) {
			if (!parameters[tIndex+1].equals("f") &&
			!parameters[tIndex+1].equals("d")) {
				PrintError.reportError(shell, "search",
						"Please specify search type after -type [f/d]");
				return true;
			}
			if (!parameters[nIndex+1].matches("\".*\"")) {
				PrintError.reportError(shell, "search",
						"Please enclose name with double quotes (\"\")");
			}
		} else {
			PrintError.reportError(shell, "search",
					"Please specify only one name");
			return true;
		}
		return false;
	}
}
