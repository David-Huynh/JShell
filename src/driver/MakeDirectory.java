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

/**
 * The MakeDirectory command is used by the user to make two new directories in
 * the file system.
 */

public class MakeDirectory extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "mkdir DIR1 DIR2 \nThis command takes in two arguments only. "
				+ "Create directories, "
				+ "each of which \nmay be relative to the current directory "
				+ "or may be a full path. If creating DIR1 \nresults in any "
				+ "kind of error, do not proceed with creating DIR 2. However, "
				+ "if \nDIR1 was created successfully, and DIR2 creation "
				+ "results in an error, then give \nback an error "
				+ "specific to DIR2.";
	}

	/**
	 * Tell the JShell to make two new directories accoriding to the user's
	 * specifications.
	 * 
	 * @param shell
	 *            The JShell the command is to be performed on
	 * @param parameters
	 *            The parameters from the interpreter the command is to work
	 *            with
	 */
	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length != 3) {
			PrintError.reportError(shell, "mkdir",
					"Invalid number of arguments.");
			return;
		}

		Directory currDir = shell.getCurrentDir();
		String[] dir1 = {};
		String[] dir2 = {};
		boolean mDir1;
		boolean mDir2;

		if (parameters[1].indexOf("/") == 0) {
			if (parameters[1].equals("/")) {
				PrintError.reportError(shell, "mkdir",
						"Directory already exits: " + parameters[1]);
			} else {
				currDir = shell.getRootDir();
				parameters[1] = parameters[1].substring(1);
			}
		}
		dir1 = parameters[1].split("/");
		mDir1 = makeDir(shell, currDir, dir1);
		if (!mDir1) {
			return;
		}
		if (parameters[2].indexOf("/") == 0) {
			if (parameters[2].equals("/")) {
				PrintError.reportError(shell, "mkdir",
						"Directory already exits: " + parameters[1]);
			} else {
				currDir = shell.getRootDir();
				parameters[2] = parameters[2].substring(1);
			}
		}
		dir2 = parameters[2].split("/");
		mDir2 = makeDir(shell, currDir, dir2);
	}

	/**
	 * Checks for valid paths and creates the directory when the path is valid.
	 * 
	 * @param shell
	 *            The JShell whose file system to work in
	 * @param currDir
	 *            The current directory
	 * @param dir
	 *            An array of names of directories to create
	 * @return Whether creation is successful
	 */
	private static boolean makeDir(JShell shell, Directory currDir,
			String[] dir) {
		for (int i = 0; i < dir.length; i++) {
			if (i + 1 == dir.length) {
				if (currDir.isSubDir(dir[i]) == -1) {
					if (!Directory.hasForbidChar(dir[i])) {
						Directory newDir = new Directory(dir[i], currDir);
						currDir.addFile(newDir);
					} else {
						PrintError.reportError(shell, "mkdir",
								"Directory contains forbidden character(s): "
										+ dir[i]);
						return false;
					}
				} else {
					PrintError.reportError(shell, "mkdir",
							"Directory already exists: " + dir[i]);
					return false;
				}
			} else {
				if (dir[i].equals("..") || dir[i].equals(".")) {
					if (dir[i].equals("..")) {
						currDir = currDir.getParentDir();
					}
				} else {
					if (currDir.isSubDir(dir[i]) == -1) {
						PrintError.reportError(shell, "mkdir",
								"No such directory: " + dir[i]);
						return false;
					} else {
						int index = currDir.isSubDir(dir[i]);
						currDir = (Directory) currDir.getDirContents()
								.get(index);
					}
				}
			}
		}
		return true;
	}
}