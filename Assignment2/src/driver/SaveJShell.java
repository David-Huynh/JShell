package driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveJShell extends ShellCommand {
	public static String getManual() {
		return "saveJShell localFilePath\n"
				+ "The above command will interact with your real file system on your computer.\n"
				+ "Saves the current working session of the shell"
				+ "so that it can be loaded in a future session";
	}
	
	/**
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
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(parameters[1]));
			out.writeObject(shell);
			out.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
