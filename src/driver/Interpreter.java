package driver;

public class Interpreter {

	public static void interpret(String userCommand, JShell shell) {
		// String message = "";
		// if (userCommand.equals("exit"))
		// message = Exit.exit(shell);
		// if (true) {
		// I'd imagine that each command class will have a try catch so
		// return(error.getMessage())
		// in the catch? or each command could also take in shell and print
		// whatever they need?
		// or each command can just print separately
		// }
		// shell.print(message);

		// Parse the userCommand into a String and a list of parameters
		String parameters[] = userCommand.strip().split(" ");

		String command = parameters[0]; // The first word is the command

		if (command.equals("exit")) {
			Exit.performOutcome(shell, parameters);
		} else if (command.equals("mkdir")) {
			MakeDirectory.performOutcome(shell, parameters);
		} else if (command.equals("cd")) {
			ChangeDirectory.performOutcome(shell, parameters);
		} else if (command.equals("ls")) {
			ListFiles.performOutcome(shell, parameters);
		} else if (command.equals("pwd")) {
			PrintWorkingDirectory.performOutcome(shell);
		} else if (command.equals("pushd")) {
			// PushDirOntoStack.performOutcome(shell, parameters);
		} else if (command.equals("popd")) {
			// PopDirFromStack.performOutcome(shell, parameters);
		} else if (command.equals("history")) {
			// PrintHistory.performOutcome(shell, parameters);
		} else if (command.equals("cat")) {
			// ConcatenateFile.performOutcome(shell, parameters);
		} else if (command.equals("echo")) {
			// Echo.performOutcome(shell, parameters);
		} else if (command.equals("man")) {
			// Manual.performOutcome(shell, parameters);
		} else {
			// something print an error here bc the command doesn't exit
		}
	}

}