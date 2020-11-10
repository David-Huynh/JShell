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

import java.util.ArrayList;

/**
 * The PrintHistory command is used by the user to print a number of the most
 * recent commands they have input.
 */

public class PrintHistory extends ShellCommand {
	
	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "history [number] \n" + "This command will print out recent "
				+ "commands, one command per " + "line. i.e. \r\n"
				+ "    1. cd ..\r\n" + "    2. mkdir textFolder\r\n"
				+ "    3. echo �Hello World�\r\n" + "    4. fsjhdfks\r\n"
				+ "    5. history\r\n"
				+ "The above output from history has two columns. The first "
				+ "column is\r\n" + "numbered such that the line with the "
				+ "highest number is the most recent command.\r\n"
				+ "The most recent command is history. The "
				+ "second column contains the actual\r\n"
				+ "command. Note: Your output should also contain as output "
				+ "any syntactical errors\r\n"
				+ "typed by the user as seen on line 4.\n"
				+ "We can truncate the output by specifying"
				+ " a number (>=0) after the command.\r\n"
				+ "For instance, if we want to only see the "
				+ "last 3 commands typed, we can type the\r\n"
				+ "following on the command line:\n" + "    history 3\r\n"
				+ "And the output will be as follows:\r\n"
				+ "    4. fsjhdfks\r\n" + "    5. history\r\n"
				+ "    6. history 3";
	}

	/**
	 * Tell the JShell to print a number of the most recent commands they have
	 * input
	 * 
	 * @param shell
	 *            The JShell the command is to be performed on
	 * @param parameters
	 *            The parameters from the interpreter the command is to work
	 *            with
	 */
	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length > 2) {
			PrintError.reportError(shell, "history",
					"Invalid number of arguments.");
			return;
		}
		ArrayList<String> his = shell.getComHis();
		int counter = 0;
		int i = 0;
		int num = 1;
		if (parameters.length == 2) {
			try {
				counter = Integer.parseInt(parameters[1]);
			} catch (Exception e) {
				PrintError.reportError(shell, "history", "Invalid number.");
				return;
			}
			if (counter > his.size() || counter < 0) {
				PrintError.reportError(shell, "history",
						"Number specified is not possible.");
				return;
			}
			i = his.size() - counter;
			num = his.size() - counter + 1;
		}
		while (i < his.size()) {
			shell.println(num + ". " + his.get(i));
			num++;
			i++;
		}
	}

}
