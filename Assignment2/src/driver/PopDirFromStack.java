package driver;

public class PopDirFromStack extends ShellCommand {

	public static String getManual() {
		return "popd \nRemove the top entry from the directory stack, and cd "
				+ "into it. The removal \n"
				+ "must be consistent as per the LIFO behavior of a stack. "
				+ "The popd command \n"
				+ "removes the top most directory from the "
				+ "directory stack and makes it the \n"
				+ "current working directory. If there is no directory onto "
				+ "the stack, then give \n"
				+ "appropriate error message.";
	}
	
	public static void performOutcome(JShell shell, String[] parameters) {
		// TODO Auto-generated method stub

	}

}
