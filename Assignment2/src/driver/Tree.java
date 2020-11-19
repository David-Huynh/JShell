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
	 * a tree. A number of tabs is indented before, which is the level of the
	 * StorageUnit in the storage system.
	 * 
	 * @param shell
	 *            The JShell the command is to be performed on
	 * @param toPrint
	 *            The StorageUnit to print out as a tree
	 * @param depth
	 *            The depth of the StorageUnit in the storage system
	 */
	private static void printTree(JShell shell, StorageUnit toPrint,
			int depth) {
		int i;
		for (i = 0; i < depth; i++) {
			shell.print("\t");
		}
		shell.println(toPrint.name);
		if (toPrint.getClass().getSimpleName().equals("Directory")) {
			for (StorageUnit unit : ((Directory) toPrint).getDirContents()) {
				printTree(shell, unit, depth + 1);
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
	 */
	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length != 1) {
			PrintError.reportError(shell, "man",
					"Invalid number of arguments.");
			return;
		}
		Tree.printTree(shell, shell.getRootDir(), 0);
	}

}
