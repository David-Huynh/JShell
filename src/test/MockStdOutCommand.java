package test;

import driver.File;
import driver.JShell;
import driver.ShellCommand;

/**
 * Mock command that produces StdOut that is referred to as cmd1
 */
public class MockStdOutCommand extends ShellCommand {

	public static JShell receivedShell;
	public static String receivedParams[];
	public static int receivedOutputType;
	public static File receivedOutputFile;

	public static boolean producesStdOut() {
		return true;
	}

	public static String getManual() {
		return "cmd1 \nI am a command that produces StdOut.";
	}

	public static void performOutcome(JShell shell, String[] parameters,
			int outputType, File outputFile) {
		shell.println("I am cmd1 and I have just been called.");
		receivedShell = shell;
		receivedParams = parameters;
		receivedOutputType = outputType;
		receivedOutputFile = outputFile;
	}

}
