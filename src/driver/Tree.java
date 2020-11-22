package driver;

/**
 * The Tree command displays the entire file system as a tree on the shell.
 */
public class Tree extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 *
	 * @return The manual
	 */
	public static String getManual() {
		return "tree";
	}

	/**
	 * Recursive helper function for performOutcome that prints a StorageUnit as
	 * a tree. A number of tabs is indented before, which is the depth of the
	 * StorageUnit in the storage system.
	 * 
	 * @param toPrint
	 *            The StorageUnit to print out as a tree
	 * @param depth
	 *            The depth of the StorageUnit in the storage system
	 * @param stdout
	 *            The StdOut to print to
	 */
	private static void printTree(StorageUnit toPrint, int depth,
			StdOut stdout) {
		int i;
		for (i = 0; i < depth; i++) {
			stdout.send("\t");
		}
		stdout.sendLine(toPrint.name);
		if (toPrint.getClass().getSimpleName().equals("Directory")) {
			for (StorageUnit unit : ((Directory) toPrint).getDirContents()) {
				printTree(unit, depth + 1, stdout);
			}
		}
	}

	/**
	 * Tell the shell to print to its command line the entire file system as a
	 * tree.
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
		if (parameters.length != 1) {
			PrintError.reportError(shell, "man",
					"Invalid number of arguments.");
			return;
		}
		Tree.printTree(shell.getRootDir(), 0, stdout);
	}

}
