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

public class ConcatenateFile extends ShellCommand {

	public static String getManual() {
		return "cat FILE1 [FILE2 ...] \n"
				+ "Display the contents of FILE1 and other files "
				+ "(i.e. File2 ...) concatenated in \n"
				+ "the shell. You may want to "
				+ "use three line breaks to separate "
				+ "the contents of one file \n" + "from the other file.";
	}

	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length < 2) {
			PrintError.reportError(shell, "cat",
					"Invalid number of arguments.");
			return;
		}

		File file1;
		File file2;
		if (parameters.length == 2) {
			// file path 1
		} else if (parameters.length == 3) {
			// optional file path 2
		}

		// Get current directory, then check if file path specified is valid

		// if valid, then check print contents from file
		// else print out the error

	}

}
