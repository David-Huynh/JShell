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

import java.util.Stack;

/**
 * The PopDirFromStack command is used by the user to remove the top entry from
 * the JShell's directory stack and make it its current directory.
 */

public class PopDirFromStack extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "popd \nRemove the top entry from the directory stack, and cd "
				+ "into it. The removal \n"
				+ "must be consistent as per the LIFO behavior of a stack. "
				+ "The popd command \n"
				+ "removes the top most directory from the "
				+ "directory stack and makes it the \n"
				+ "current working directory. If there is no directory onto "
				+ "the stack, then give \n" + "appropriate error message.";
	}

	/**
	 * Tell the JShell to remove the top entry from the directory stack and make
	 * it its current directory.
	 * 
	 * @param shell
	 *            The JShell the command is to be performed on
	 * @param parameters
	 *            The parameters from the interpreter the command is to work
	 *            with
	 */
	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length != 1) {
			PrintError.reportError(shell, "popd",
					"Invalid number of arguments.");
			return;
		}

		Stack<Directory> dirStack = shell.getDirStack();

		if (dirStack.size() == 0) {
			PrintError.reportError(shell, "popd", "Directory stack is empty.");
			return;
		}

		Directory topDir = dirStack.pop();
		// System.out.println(topDir.name);

		shell.setCurrentDir(topDir);

	}

}