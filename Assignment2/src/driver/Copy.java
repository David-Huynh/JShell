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

public class Copy extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "cp OLDPATH NEWPATH\n"
				+ "Like mv, but don’t remove OLDPATH. If OLDPATH\n"
				+ "is a directory, recursively copy the contents.";
	}

	/**
	 * Make a copy of StorageUnit in Storage in a given directory.
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
		if (outputType != 0) {
			PrintError.reportError(shell, "cp",
					"This command does not produce stdout.");
		}
		if (parameters.length != 3) {
			PrintError.reportError(shell, "cp", "Invalid number of arguments.");
			return;
		}
		// Just call TransferFile, as its job is to also copy files.
		TransferFile.performOutcome(shell, parameters, outputType, outputFile);
	}

}
