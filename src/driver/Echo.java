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

public class Echo extends ShellCommand {
	private static int contains(String[] parameters, String keyword){
		int counter = 0;
		for (int i = 1; i < parameters.length; i++){
			if (parameters[i].contains(keyword)){
				counter += parameters[i].length();
			}
		}
		return counter;
	}
	private static String[] parseParameters(String [] parameters){
		String parsedParams [] = {"",""};
		boolean closedString = false;
		for (int i = 1; i < parameters.length;i++) {
			if (!closedString) {
				if (parameters[i].charAt(0) == '>') {
					closedString = true;
					if(parameters[i].charAt(parameters[i].length()-1) != '>'){
						parsedParams[1] = parameters[i].replace(">", "");
					}
				} else if (parameters[i].charAt(parameters[i].length() - 1) == '>') {
					closedString = true;
					parsedParams[0] += parameters[i].replace(">", "");
					continue;
				}
				if (!closedString) {
					parsedParams[0] += parameters[i].replace(">", "");
				}
			} else {
				parsedParams[1] = parameters[i].replace(">", "");
			}
		}
		return parsedParams;
	}
	public static String getManual() {
		return "echo STRING [> OUTFILE]\n"
				+ "If OUTFILE is not provided, print STRING on the shell.\n"
				+ "Otherwise, put STRING into file OUTFILE.\n"
				+ "STRING is a string of characters surrounded by double quotation marks.\n"
				+ "This creates a new file if OUTFILE does not exists and erases "
				+ "the old contents if OUTFILE already exists.\n "
				+ "In either case, the only thing in OUTFILE should be STRING.\n"
				+ "echo STRING >> OUTFILE\n"
				+ "appends to OUTFILE instead of overwrite";
	}
	public static void performOutcome(JShell shell, String[] parameters) {
		int numArrow = contains(parameters,">");
		if (numArrow == 0){ //Print string to shell command
			for (int i = 1; i < parameters.length; i++){
				shell.println(parameters[i]);
			}
		}
		String [] parsedParams = parseParameters(parameters);
		if (numArrow == 1){//Overwrite file with string

		}else{//Append to file with string

		}

	}

}
