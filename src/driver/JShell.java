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
import java.util.Stack;

/**
 * JShell is an interactive Unix-like shell that allows a user to use ten
 * different commands to manipulate a virtual file system containing files and
 * directories.
 */

public class JShell {

	/** Root directory stores everything */
	private Storage rootDir;
	/** Current directory user is working in */
	private Directory currentDir;
	/** Whether the JShell is active, turned off if the user wishes to exit */
	private boolean isActive;
	/** Directory stack of the shell */
	private Stack<Directory> dirStack;

	/**
	 * Initializes an instance of the JShell, initializes all private variables
	 */
	public JShell() {
		this.rootDir = Storage.createNewStorage();
		this.currentDir = this.rootDir; // by default current directory is root
		this.dirStack = new Stack<Directory>();
		this.isActive = true;
	}

	/**
	 * Public getter method for the current directory
	 * 
	 * @return The current directory of the shell
	 */
	public Directory getCurrentDir() {
		return this.currentDir;
	}

	/**
	 * Public setter method for the current directory
	 * 
	 * @param currentDir
	 *            The given directory to be the new current directory
	 */
	public void setCurrentDir(Directory currentDir) {
		this.currentDir = currentDir;
	}

	/**
	 * Public getter method for the root directory
	 * 
	 * @return The root directory of the shell
	 */
	public Directory getRootDir() {
		return this.rootDir;
	}

	/**
	 * Public getter method for the directory stack
	 * 
	 * @return The directory stack of the shell
	 */
	public Stack<Directory> getDirStack() {
		return this.dirStack;
	}

	/**
	 * Public exit method which the ShellCommand Exit uses
	 */
	public void exit() {
		this.isActive = false;
	}

	/**
	 * Prints a given message as a line on the shell
	 * 
	 * @param message
	 *            The message to be printed as a line
	 */
	public void println(String message) {
		System.out.println(message);
	}

	/**
	 * Prints a given message as a on the shell
	 * 
	 * @param message
	 *            The message to be printed
	 */
	public void print(String message) {
		System.out.print(message);
	}

	/**
	 * Prints a given error message on the shell
	 * 
	 * @param errMsg
	 *            The error message to be printed
	 */
	public void printError(String errMsg) {
		System.out.println(errMsg);
	}

	/**
	 * Continually prompts the user for input and sends to Interpreter
	 */
	public void run() {
		String userCommand;
		Scanner userInput = new Scanner(System.in);
		while (this.isActive) {
			this.print(currentDir.getName() + ">");
			userCommand = userInput.nextLine();
			Interpreter.interpret(userCommand, this);
		}
		userInput.close();
	}

	/**
	 * Creates a new instance of the JShell and runs it
	 */
	public static void main(String[] args) {
		JShell shell = new JShell();
		shell.run();
	}
}