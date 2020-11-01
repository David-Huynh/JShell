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

public class PushDirOntoStack extends ShellCommand {

	public static String getManual() {
		return "pushd DIR \nSaves the current working directory by pushing "
				+ "onto directory stack and then \n"
				+ "changes the new current working directory to DIR. The push "
				+ "must be \n"
				+ "consistent as per the LIFO behavior of a stack. The pushd "
				+ "command saves \n"
				+ "the old current working directory in directory stack so that"
				+ " it can be returned \n"
				+ "to at any time (via the popd command). The size of the "
				+ "directory stack \n"
				+ "is dynamic and dependent on the pushd and the popd "
				+ "commands.";
	}

	public static void performOutcome(JShell shell, String[] parameters) {
		// TODO Auto-generated method stub

	}

}
