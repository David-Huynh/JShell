package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadJShell extends ShellCommand {
	public static String getManual() {
		return "loadJShell localFilePath\n"
				+ "Loads a previously saved JShell session onto a fresh JShell sessions.\n"
				+ "This command only works on fresh JShell sessions.";
	}

	/**
	 * 
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
		if (shell.getComHis().size() == 1) {
			try {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(parameters[1]));
				JShell newShell = (JShell) in.readObject();
				shell.updateShell(newShell);
				in.close();
			} catch (IOException e) {
				System.out.println("File not found");
			} catch (ClassNotFoundException e) {
				System.err.println(e.getMessage());
			}
		} else {
			PrintError.reportError(shell,
					"loadJShell: there are unsaved changes in this session,"
							+ "\nstart a fresh shell to load a previously saved session.");
		}
	}

}
