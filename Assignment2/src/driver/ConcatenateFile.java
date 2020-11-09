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

import java.util.ArrayList;

/**
 * The ConcatenateFile command is used to print the contents of one or more
 * Files to the shell's command line, so the user can find out what is in
 * specific files.
 */

public class ConcatenateFile extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "cat FILE1 [FILE2 ...] \n"
				+ "Display the contents of FILE1 and other files "
				+ "(i.e. File2 ...) concatenated in \n"
				+ "the shell. You may want to "
				+ "use three line breaks to separate "
				+ "the contents of one file \n" + "from the other file.";
	}

	/**
	 * Tell the JShell to print the contents of one or more files.
	 * 
	 * @param shell
	 *            The JShell the command is to be performed on
	 * @param parameters
	 *            The parameters from the interpreter the command is to work
	 *            with
	 */
	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length < 2) {
			PrintError.reportError(shell, "cat",
					"Invalid number of arguments.");
			return;
		}
		String path[] = parameters[1].split("/");
		if (!catFiles(path, shell)) {
			return;
		}
		int i;
		for (i = 2; i < parameters.length; i++) {
			String path2[] = parameters[i].split("/");
			shell.print("\n\n\n");
			if (!catFiles(path2, shell)) {
				return;
			}
		}
	}

	/**
	 * Concatenates files in a given path to a directory
	 * 
	 * @param path
	 *            The path to the directory
	 * @param shell
	 *            The JShell in use
	 * @return Whether it was successful, i.e. no errors
	 */
	private static boolean catFiles(String[] path, JShell shell) {
		Directory currDir = shell.getCurrentDir();
		ArrayList<StorageUnit> contents = currDir.getDirContents();
		for (int i = 0; i < path.length; i++) {
			if (i + 1 == path.length) {
				int fIndex = currDir.containsFile(path[i]);
				if (fIndex == -1) {
					PrintError.reportError(shell, "cat",
							"Does not contain file: " + path[i]);
					return false;
				} else {
					File file = (File) contents.get(fIndex);
					file.print(shell);
				}
			} else {
				if (path[i].equals("..") || path[i].equals(".")) {
					if (path[i].equals("..")) {
						currDir = currDir.getParentDir();
					}
				} else {
					int index = currDir.isSubDir(path[i]);
					if (index == -1) {
						PrintError.reportError(shell, "cat",
								"Invalid Directory.");
						return false;
					} else {
						currDir = (Directory) contents.get(index);
						contents = currDir.getDirContents();
					}
				}
			}
		}
		return true;
	}

}
