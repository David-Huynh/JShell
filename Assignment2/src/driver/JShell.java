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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * JShell is an interactive Unix-like shell that allows a user to use eleven
 * different commands to manipulate a virtual file system containing files and
 * directories.
 */

public class JShell implements Serializable {

	/** Root directory stores everything */
	private Storage rootDir;
	/** Current directory user is working in */
	private Directory currentDir;
	/** Whether the JShell is active, turned off if the user wishes to exit */
	private transient boolean isActive;
	/** Directory stack of the shell */
	private Stack<Directory> dirStack;
	/**
	 * HashMap that maps commands to the Class that represents it e.g. ls maps
	 * to ListFiles
	 */
	private HashMap<String, Class> cmdToClass;
	/** ArrayList that stores the history of commands */
	private ArrayList<String> comHis;

	/**
	 * Initializes an instance of the JShell, initializes all private variables
	 */
	public JShell() {
		this.rootDir = Storage.createNewStorage();
		this.currentDir = this.rootDir.getRoot(); // by default current
													// directory is root
		this.dirStack = new Stack<Directory>();
		this.isActive = true;
		this.cmdToClass = new HashMap<String, Class>();
		this.populateCmdToClass();
		this.comHis = new ArrayList<String>();
	}
	public void updateShell(JShell newShell) {
		this.rootDir = newShell.rootDir;
		this.currentDir = newShell.currentDir;
		this.dirStack = newShell.dirStack;
		this.cmdToClass = newShell.cmdToClass;
		this.comHis = newShell.comHis;
	}
	/**
	 * Helper method for JShell initializer, populates the JShell's command to
	 * Class HashMap
	 */
	private void populateCmdToClass() {
		try { // Would need to update only this when there come new commands
			this.cmdToClass.put("exit", Class.forName("driver.Exit"));
			this.cmdToClass.put("mkdir", Class.forName("driver.MakeDirectory"));
			this.cmdToClass.put("cd", Class.forName("driver.ChangeDirectory"));
			this.cmdToClass.put("ls", Class.forName("driver.ListFiles"));
			this.cmdToClass.put("pwd",
					Class.forName("driver.PrintWorkingDirectory"));
			this.cmdToClass.put("pushd",
					Class.forName("driver.PushDirOntoStack"));
			this.cmdToClass.put("popd",
					Class.forName("driver.PopDirFromStack"));
			this.cmdToClass.put("history",
					Class.forName("driver.PrintHistory"));
			this.cmdToClass.put("cat", Class.forName("driver.ConcatenateFile"));
			this.cmdToClass.put("echo", Class.forName("driver.Echo"));
			this.cmdToClass.put("man", Class.forName("driver.Manual"));
			this.cmdToClass.put("saveJShell",
					Class.forName("driver.SaveJShell"));
			this.cmdToClass.put("loadJShell",
					Class.forName("driver.LoadJShell"));
			this.cmdToClass.put("search", Class.forName("driver.Search"));
			this.cmdToClass.put("tree", Class.forName("driver.Tree"));
			this.cmdToClass.put("rm", Class.forName("driver.Remove"));
			//this.cmdToClass.put("mv", Class.forName("driver.Move"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
	 * @return The root directory of the shell that lives in the single instance
	 *         of Storage
	 */
	public Directory getRootDir() {
		return this.rootDir.getRoot();
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
	 * Public getter method for the command to Class HashMap
	 * 
	 * @return The command to Class HashMap
	 */
	public HashMap<String, Class> getCmdToClass() {
		return this.cmdToClass;
	}

	/**
	 * Public getter method for the history of commands
	 *
	 * @return The ArrayList of the history of commands
	 */
	public ArrayList<String> getComHis() {
		return this.comHis;
	}

	/**
	 * Public setter method for appending to the history of commands
	 * 
	 * @param command
	 *            The command to be added
	 */
	public void addCom(String command) {
		this.comHis.add(command);
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
			addCom(userCommand);
			if (userCommand.strip() == "") {
				PrintError.reportError(this, "Error: no command entered.");
			} else {
				Interpreter.interpret(userCommand, this);
			}
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