package driver;

public class Interpreter {
	
	public static void interpret(String userCommand, JShell shell) {
		String message = "";
		if (userCommand.equals("exit")) message = Exit.exit(shell);
		if(true){
			//I'd imagine that each command class will have a try catch so return(error.getMessage())
				//in the catch? or each command could also take in shell and print whatever they need?
				//or each command can just print separately
		}
		shell.print(message);
	}
	
}
