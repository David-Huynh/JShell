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

public class JShell {

	private Directory rootDir; // root directory stores everything 
	private Directory currentDir;

	public JShell() { // Shell instance initializer 
		this.rootDir = new Directory();
		this.currentDir = this.rootDir; // by default current directory is root
	}
	
	public static void main(String[] args) {
		JShell shell = new JShell();
	}
}