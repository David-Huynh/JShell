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
