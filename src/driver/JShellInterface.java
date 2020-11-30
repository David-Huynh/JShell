package driver;

import java.util.Stack;

/**
 * The Interface used for the real JShell and MockJShells used for testing.
 */
public interface JShellInterface {

	/**
	 * Getter method for this JShell's root directory
	 * 
	 * @return The root directory
	 */
	Directory getRootDir();

	/**
	 * Setter method for this JShell's current directory
	 * 
	 * @param dir
	 *            The new directory
	 */
	void setCurrentDir(Directory dir);

	/**
	 * Getter method for this JShell's current directory
	 * 
	 * @return The current directory
	 */
	Directory getCurrentDir();

	/**
	 * Print and error message to the command line
	 * 
	 * @param string
	 *            The error message
	 */
	void printError(String string);

	/**
	 * Public getter method for the directory stack
	 * 
	 * @return The directory stack of the shell
	 */
	Stack<Directory> getDirStack();
}
