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

public class Manual extends ShellCommand {

	public static String getManual() { // Well this is quite meta
		return "man CMD [CMD2 …]\n" + "Print documentation for CMD(s)";
	}

	public static void performOutcome(JShell shell, String[] parameters) {

		shell.println("");

		String command = parameters[1]; // only accounts for one parameter for
										// now, need to account for multiple
										// using a while loop TODO

		if (command.equals("exit")) {
			System.out.println(Exit.getManual());
		} else if (command.equals("mkdir")) {
			System.out.println(MakeDirectory.getManual());
		} else if (command.equals("cd")) {
			System.out.println(ChangeDirectory.getManual());
		} else if (command.equals("ls")) {
			System.out.println(ListFiles.getManual());
		} else if (command.equals("pwd")) {
			System.out.println(PrintWorkingDirectory.getManual());
		} else if (command.equals("pushd")) {
			System.out.println(PushDirOntoStack.getManual());
		} else if (command.equals("popd")) {
			System.out.println(PopDirFromStack.getManual());
		} else if (command.equals("history")) {
			System.out.println(PrintHistory.getManual());
		} else if (command.equals("cat")) {
			System.out.println(ConcatenateFile.getManual());
		} else if (command.equals("echo")) {
			System.out.println(Echo.getManual());
		} else if (command.equals("man")) {
			System.out.println(Manual.getManual());
		} else {
			PrintError.reportError(shell, "man",
					command + " is not a valid command.");
		}

		System.out.println("");
	}

}
