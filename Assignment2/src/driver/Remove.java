package driver;

/**
 * The Remove command is used by the user to remove a specified directory in the
 * file system.
 */

public class Remove extends ShellCommand {

	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "rm DIR\nRemoves the directory from the file system. This also "
				+ "removes all the\nchildren of DIR.";
	}

	/**
	 * Tell the JShell to remove a specified file/directory.
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
			PrintError.reportError(shell, "cd",
					"This command does not produce stdout.");
		}

		if (parameters.length != 2) {
			PrintError.reportError(shell, "rm", "Invalid number of parameters");
			return;
		}

		Path toDelete = new Path(parameters[1]);
		Directory startDir;

		if (toDelete.isAbsolute()) {
			startDir = shell.getRootDir();
		} else {
			startDir = shell.getCurrentDir();
		}

		Directory parentOfDeleted = toDelete.cyclePath(0, startDir, shell);
		StorageUnit toDeleteDir = toDelete
				.determineFinalElement(parentOfDeleted);

		if (toDeleteDir == null || !toDeleteDir.getClass().getSimpleName()
				.equals("Directory")) {
			PrintError.reportError(shell, "rm", "Cannot delete '"
					+ parameters[1] + "', no such directory.");
			return;
		}

		if (toDeleteDir.checkParents(shell)) {
			PrintError.reportError(shell, "rm",
					"Cannot remove current working directory or any of its ancestors");
			return;
		}
		toDeleteDir.getParentDir().getDirContents().remove(toDeleteDir);
	}
}