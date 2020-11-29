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
 * The MakeDirectory command is used by the user to make new directories in the
 * file system.
 */

public class MakeDirectory extends ShellCommand {

	/**
	 * Returns if this command produces StdOut. (used by the Interpreter to know
	 * whether or not to make a new file)
	 * 
	 * @return Whether or not the command produces StdOut
	 */
	public static boolean producesStdOut() {
		return false;
	}
	
	/**
	 * Provides the manual for how to use this command
	 *
	 * @return The manual
	 */
	public static String getManual() {
		return "mkdir DIR ... \n" + "Create directories, "
				+ "each of which may be relative to the current directory "
				+ "or \nmay be a full path. If creating a DIR results in any "
				+ "kind of error, do not proceed "
				+ "\nwith creating the rest. However, "
				+ "if some DIRs are created "
				+ "successfully, and another \ncreation "
				+ "results in an error, then give back an error "
				+ "specific to this DIR.";
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
		if (parameters.length < 2) {
			PrintError.reportError(shell, "mkdir",
					"Invalid number of arguments.");
			return;
		}
		Directory currDir;
		Path path = new Path("");
		for (int i = 1; i < parameters.length; i++) {
			path.setPath(parameters[i]);
			String[] elements = path.getPathElements();
			if (path.isAbsolute()) {
				currDir = shell.getRootDir();
			} else {
				currDir = shell.getCurrentDir();
			}
			Directory parent = (Directory) path.verifyPath(shell, true);
			//System.out.println("DIR NAME: "+parent.getName());
			if (parent == null) {
				PrintError.reportError(shell, "mkdir",
						"Directory does not exits: " + parameters[i]);
				return;
			} else {
				if (path.getPath().equals("/")) {
					PrintError.reportError(shell, "mkdir",
							"Root directory already exits!");
					return;
				} else {
					if (parent.isSubDir(elements[elements.length - 1]) == -1) {
						// create dir
						if (!StorageUnit
								.hasForbidChar(elements[elements.length - 1])) {
							if (parent.containsFile(
									elements[elements.length - 1]) == -1) {
								Directory newDir = new Directory(
										elements[elements.length - 1], parent);
								parent.addFile(newDir);
							} else {
								PrintError.reportError(shell, "mkdir",
										"Directory name cannot be the same as filename: "
												+ elements[elements.length
														- 1]);
								return;
							}
						} else {
							PrintError.reportError(shell, "mkdir",
									"Directory name contains forbidden "
											+ "character(s): "
											+ elements[elements.length - 1]);
							return;
						}
					} else {
						PrintError.reportError(shell, "mkdir",
								"Directory already exits: " + parameters[i]);
						return;
					}
				}
			}
		}
	}
}