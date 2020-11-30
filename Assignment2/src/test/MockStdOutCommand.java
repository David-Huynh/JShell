package test;

import driver.File;
import driver.JShell;
import driver.ShellCommand;

public class MockStdOutCommand extends ShellCommand {

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
		receivedParams = parameters;
		receivedOutputType = outputType;
		receivedOutputFile = outputFile;
	}

}
