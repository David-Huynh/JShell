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

import java.util.Scanner;

public class JShell {

	private Directory rootDir; // root directory stores everything
	private Directory currentDir;
	private boolean isActive; // turn this off if user wants to exit

	public JShell() { // Shell instance initializer
		this.rootDir = new Directory();
		this.rootDir.setDirName("root");
		this.rootDir.setParentDir(rootDir); // parent of root directory is
											// always the root
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
		System.out.println(message);
	}

	public void run() { // continually prompts the user for input and sends to
						// Interpreter
		String userCommand;
		Scanner userInput = new Scanner(System.in);
		while (this.isActive) {
			System.out.print(currentDir.getDirName() + ">");
			userCommand = userInput.nextLine();
			Interpreter.interpret(userCommand, this);
		}
		userInput.close();
	}

	public static void main(String[] args) {
		JShell shell = new JShell();
		shell.run();
	}
}