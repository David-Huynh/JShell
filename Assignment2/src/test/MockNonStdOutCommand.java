package test;

import driver.File;
import driver.JShell;
import driver.ShellCommand;

/**
 * Mock command that doesn't produce StdOut that is referred to as cmd2
 */
public class MockNonStdOutCommand extends ShellCommand {

	public static JShell receivedShell;
	public static String receivedParams[];
	public static int receivedOutputType;
	public static File receivedOutputFile;

	public static boolean producesStdOut() {
		return false;
	}

	public static String getManual() {
		return "cmd2 \nI am a command that does not produce StdOut.";
	}

	public static void performOutcome(JShell shell, String[] parameters,
			int outputType, File outputFile) {
		shell.println("I am cmd2 and I have just been called.");
		receivedShell = shell;
		receivedParams = parameters;
		receivedOutputType = outputType;
		receivedOutputFile = outputFile;
	}

}
