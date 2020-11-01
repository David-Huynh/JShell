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

public class ChangeDirectory extends ShellCommand {

	public static String getManual() {
		return "cd DIR \nChange directory to DIR, which may be relative to the "
				+ "current directory or \nmay be a full path. As with Unix, .. "
				+ "means a parent directory and a . means \nthe current "
				+ "directory. The directory must be /, the forward slash. The "
				+ "foot of \nthe file system is a single slash: /.  ";
	}

	// function to execute command for cd
	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length != 2) {
			PrintError.reportError(shell, "cd", "Invalid number of arguments.");
			return;
		}

		Directory currDir = shell.getCurrentDir();
		String[] subDir = {};

		subDir = parameters[1].split("/");

		for (int i = 0; i < subDir.length; i++) {
			if (subDir[i].equals("..") || subDir[i].equals(".")) {
				if (subDir[i].equals("..")) {
					currDir = currDir.getParentDir();
					shell.setCurrentDir(currDir);
				}
			} else {
				if (currDir.isSubDir(subDir[i]) == -1) {
					PrintError.reportError(shell, "cd",
							"That is not a valid directory.");
					return;
				} else {
					int index = currDir.isSubDir(subDir[i]);
					currDir = (Directory) currDir.getDirContents().get(index);
					shell.setCurrentDir(currDir);
				}
			}
		}
	}
}
