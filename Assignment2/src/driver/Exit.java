package driver;

public class Exit extends ShellCommand {
	
	public static void performOutcome(JShell shell, String[] parameters) {
		shell.exit();
	}

}