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
import java.util.Arrays;
import java.util.List;

public class Echo extends ShellCommand {

	public static String getManual() {
		return "echo STRING [> OUTFILE]\n"
				+ "If OUTFILE is not provided, print STRING on the shell.\n"
				+ "Otherwise, put STRING into file OUTFILE.\n"
				+ "STRING is a string of characters "
				+ "surrounded by double quotation marks.\n"
				+ "This creates a new file if "
				+ "OUTFILE does not exists and erases "
				+ "the old contents if OUTFILE already exists.\n"
				+ "In either case, the only "
				+ "thing in OUTFILE should be STRING.\n"
				+ "echo STRING >> OUTFILE\n"
				+ "Like the previous command, but"
				+ "appends to OUTFILE instead of overwrites";
	}
	// Counts number of '>' in parameters
	private static int numArrow(String[] parameters) {
		int counter = 0;
		for (int i = 1; i < parameters.length; i++) {
			for (int c = 0; c < parameters[i].length(); c++){
				if (parameters[i].charAt(c) == '>') {
					counter += 1;
				}
			}
		}
		return counter;
	}
	// Parses input parameters into 2 pieces "String" = [0] and "FileName" = [1]
	private static String[] parseParameters(String[] parameters) {
		String[] parsedParams = {"", ""};
		boolean closedString = false;
		if (parameters[1].contains(">") &&
				parameters[1].charAt(parameters[1].length() - 1)!='>'&&
				parameters[1].charAt(0)!='>'){
			return parameters[1].split(">+");
		}
		for (int i = 1; i < parameters.length; i++) {
			if (!closedString) {// Checks if '>' separator has been passed
				if (parameters[i].charAt(0) == '>') {// Checks for leading '>'
					closedString = true;
					if (parameters[i] // Checks for ending '>'
							.charAt(parameters[i].length() - 1) != '>') {
						parsedParams[1] = parameters[i].replace(">", "");
					}
				} else if (parameters[i] // Checks for ending'>'
						.charAt(parameters[i].length() - 1) == '>') {
					closedString = true;
					parsedParams[0] += parameters[i].replace(">", "");
					continue;
				}
				if (!closedString) {// Checks if '>' has been passed
					parsedParams[0] += parameters[i].replace(">", "");
				}
			} else {
				// Adds the second part after '>' to second index
				parsedParams[1] = parameters[i].replace(">", "");
			}
		}
		return parsedParams;
	}

	private static boolean errorHandle(JShell shell, String[] parsedParams,
			int numArrow) {
		// Check for empty string
		if (parsedParams[0].length() <= 1) {
			PrintError.reportError(shell, "echo", "No string attached; string "
					+ "must be surrounded by \"\"");
			return true;
		}
		// Checks for string to be surrounded by "."
		if (parsedParams[0].charAt(0) != '\"' || parsedParams[0]
				.charAt(parsedParams[0].length() - 1) != '\"') {
			PrintError.reportError(shell, "echo",
					"String must be surrounded " + "by \"\"");
			return true;
		}
		// Checks for empty file name
		if (numArrow != 0) {
			if (parsedParams[1].equals("")) {
				PrintError.reportError(shell, "echo",
						"FileName/OutFile cannot be empty");
				return true;
			}
		}
		// Check for double quotes in string
		if (parsedParams[0].substring(1, parsedParams[0].length() - 1)
				.contains("\"")) {
			PrintError.reportError(shell, "echo",
					"\\\" is an invalid string character\"");
			return true;
		}
		return false;
	}
	private static Directory directory(JShell shell, String filePath){
		Directory currDir = shell.getCurrentDir();
		String[] dir1;

		if (filePath.indexOf("/") == 0) {
			if (filePath.equals("/")) {
				PrintError.reportError(shell, "echo",
						"filename must be provided" + filePath);
			} else {
				currDir = shell.getRootDir();
				filePath = filePath.substring(1);
			}
		}
		dir1 = filePath.split("/");
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < dir1.length - 1; i++){
			list.add(dir1[i]);
			list.get(i);
		}
		Directory dir = currDir.cycleDir(list, -1, currDir, shell);//Doesn't return null for invalid path
		if (dir == null){
			PrintError.reportError(shell, "echo", "directory does not exist");
			return null;
		}else{
			return dir;
		}
	}
	public static void performOutcome(JShell shell, String[] parameters) {
		int numArrow = numArrow(parameters);
		String[] parsedParams = parseParameters(parameters);
		Directory dir = directory(shell, parsedParams[1]);
		String fileName = parsedParams[1].split("/")[parsedParams[1].split("/").length-1];

		if (dir == null){
			return;
		}
		int index = dir.containsFile(fileName);
		if (errorHandle(shell, parsedParams, numArrow)) {
			return;
		}

		parsedParams[0] = parsedParams[0].substring(1,
				parsedParams[0].length() - 1);
		if (numArrow == 0) { // Print string to shell command
			shell.println(parsedParams[0]);
			return;
		}
		if (index != -1) { // File does not exist
			File nf = (File) dir.getFile(index);
			if (numArrow == 1) {// Overwrite file with string
				nf.overwrite(fileName);
			} else {// Append to file with string
				nf.append(fileName);
			}
		} else { // File exists
			File nf = new File(fileName, parsedParams[0], dir);
			dir.addFile(nf);
		}
	}
}
