package driver;

public class Manual extends ShellCommand {

	public static void performOutcome(JShell shell, String[] parameters) {
		// TODO Auto-generated method stub

		System.out.println("");
		
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
			shell.print("error: not a valid command");
		}
		
		
		System.out.println("");
	}

}
