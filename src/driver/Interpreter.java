package driver;

public class Interpreter {
	
	public static void interpret(String userCommand, JShell shell) {
		String message = "";
		if (userCommand.equals("exit")) {
			message = Exit.exit(shell);
		}else{
			//Filler
		}
		shell.print(message);
	}
	
}
