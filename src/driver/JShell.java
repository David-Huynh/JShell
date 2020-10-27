// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name:
// UT Student #:
// Author:
//
// Student2:
// UTORID user_name:
// UT Student #:
// Author:
//
// Student3:
// UTORID user_name:
// UT Student #:
// Author:
//
// Student4:
// UTORID user_name:
// UT Student #:
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import java.util.Scanner;

public class JShell {

	private Directory rootDir; // root directory stores everything
	private Directory currentDir;
	private boolean isActive; // turn this off if user wants to exit

	public JShell() { // Shell instance initializer
		this.rootDir = new Directory();
		this.rootDir.setDirName("root");
		this.rootDir.setParentDir(rootDir); // parent of root directory is always the root
		this.rootDir.setDirName("root");
		this.currentDir = this.rootDir; // by default current directory is root
		this.isActive = true;
	}

	public Directory getCurrentDir() {
		return this.currentDir;
	}

	public void setCurrentDir(Directory currentDir) {
		this.currentDir = currentDir;
	}
	
	public Directory getRootDir() {
      return this.rootDir;
    }
	
	public void exit() { // Exit class calls this
		this.isActive = false;
	}

	public void print(String message) {
		System.out.print(message);
	}

	public void run() { // continually prompts the user for input and sends to
						// Interpreter
		String userCommand;
		while (this.isActive) {
			Scanner userInput = new Scanner(System.in);
			userCommand = userInput.nextLine();
			Interpreter.interpret(userCommand, this);
		}
	}

	public static void main(String[] args) {
		JShell shell = new JShell();
		shell.run();
	}
}