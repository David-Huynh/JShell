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

public class Interpreter {

	public static void interpret(String userCommand, JShell shell) {

		// Parse the userCommand into a String and a list of parameters
		String parameters[] = userCommand.strip().split(" +");

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
			PrintWorkingDirectory.performOutcome(shell, parameters);
		} else if (command.equals("pushd")) {
			PushDirOntoStack.performOutcome(shell, parameters);
		} else if (command.equals("popd")) {
			PopDirFromStack.performOutcome(shell, parameters);
		} else if (command.equals("history")) {
			PrintHistory.performOutcome(shell, parameters);
		} else if (command.equals("cat")) {
			ConcatenateFile.performOutcome(shell, parameters);
		} else if (command.equals("echo")) {
			Echo.performOutcome(shell, parameters);
		} else if (command.equals("man")) {
			Manual.performOutcome(shell, parameters);
		} else {
			PrintError.reportError(shell,
					"Error: " + command + " is not a valid command.");
		}
	}

}