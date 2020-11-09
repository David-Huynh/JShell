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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * The Interpreter interprets user commands from the JShell to perform the
 * user's desired outcomes
 */

public class Interpreter {

	/**
	 * Splits a given user command into an array of strings. Phrases written in
	 * quotes ("") will remain exactly as is, otherwise any spaces will be
	 * interpreted as a gap in between parameters.
	 * 
	 * @param userCommand
	 *            A command the user has input into the JShell
	 * @return The array of strings ready to be passed on to a ShellCommand
	 */
	public static String[] splitCmdIntoParams(String userCommand) {
		String[] parameters = new String[0];
		int i;
		boolean quoteMode = false;
		String currString = "";
		for (i = 0; i < userCommand.length(); i++) {
			if (quoteMode) {
				if (userCommand.charAt(i) == '"') {
					currString = currString + userCommand.charAt(i);
					quoteMode = false;
				} else {
					currString = currString + userCommand.charAt(i);
				}
			} else {
				if (userCommand.charAt(i) == '"') {
					quoteMode = true;
					currString = currString + userCommand.charAt(i);
				} else if (userCommand.charAt(i) != ' ') {
					currString = currString + userCommand.charAt(i);
				} else if (userCommand.charAt(i) == ' ' && currString != "") {
					parameters = Arrays.copyOf(parameters,
							parameters.length + 1);
					parameters[parameters.length - 1] = currString;
					currString = "";
				}
			}
		}
		if (currString != "") {
			parameters = Arrays.copyOf(parameters, parameters.length + 1);
			parameters[parameters.length - 1] = currString;
			currString = "";
		}
		return parameters;
	}

	/**
	 * Calls the appropriate commands to carry out the user's desired outcomes
	 * according to a given command, according to the JShell's command to Class
	 * HashMap
	 * 
	 * @param userCommand
	 *            A command the user has input into the JShell
	 * @param shell
	 *            The specific instance of JShell the user is using
	 */
	public static void interpret(String userCommand, JShell shell) {
		// Parse the userCommand into a String and a list of parameters
		// String parameters[] = userCommand.strip().split(" +");
		String parameters[] = Interpreter.splitCmdIntoParams(userCommand);
		String command = parameters[0]; // The first word is the command
		if (shell.getCmdToClass().containsKey(command)) {
			try {
				Method perform = shell.getCmdToClass().get(command)
						.getDeclaredMethod("performOutcome", shell.getClass(),
								String[].class);
				try {
					perform.invoke(null, shell, parameters);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} else {
			PrintError.reportError(shell,
					"Error: " + command + " is not a valid command.");
		}
	}

}