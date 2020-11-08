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

/**
 * A StorageUnit stores data and lives in a JShell's storage system. Subclasses
 * of StorageUnit are File and Directory.
 */

abstract class StorageUnit {

	/** The name that the StorageUnit is identified by */
	protected String name;
	/** The directory the StorageUnit lives in */
	protected Directory parentDir;

	/**
	 * Public getter method for the name
	 * 
	 * @return The name of the StorageUnit
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Public setter method for the namme
	 * 
	 * @param name
	 *            The name to be changed to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Public getter method for the parent directory
	 * 
	 * @return The StorageUnit's location
	 */
	public Directory getParentDir() {
		return parentDir;
	}

	/**
	 * Public getter method for the absolute path of a storage unit
	 * 
	 * @return The absolute path of a StorageUnit as a path variable
	 */
	public Path getFullPath(JShell shell) {
		String pathString = "/";
		StorageUnit currDir = this;

		if (currDir != shell.getRootDir()) {
			pathString = currDir.getParentDir().name + "/" + currDir.name
					+ pathString;
			// append name of parent directory to current directory
			currDir = currDir.getParentDir();

			while (currDir != shell.getRootDir()) {
				pathString = currDir.getParentDir().name + "/" + pathString;
				// keep appending parent directory until we hit root
				currDir = currDir.getParentDir();
			}
		}
		if (!pathString.equals("/")) {
			return new Path(pathString.substring(1)); // return absolute path
		} else {
			return new Path(pathString);
		}
	}

	/**
	 * Public setter method for the parent directory
	 * 
	 * @param parent
	 *            The new location of the StorageUnit
	 */
	public void setParentDir(Directory parent) {
		this.parentDir = parent;
	}

	/**
	 * Checks if a string has any of the characters not allowed in the name of a
	 * StorageUnit
	 * 
	 * @param filename
	 *            The string to be checked for forbidden characters
	 * @return If the name does indeed have problematic characters
	 */
	public static boolean hasForbidChar(String filename) {
		return (filename.contains("/") || filename.contains(".")
				|| filename.contains(" ") || filename.contains("!")
				|| filename.contains("@") || filename.contains("#")
				|| filename.contains("$") || filename.contains("%")
				|| filename.contains("^") || filename.contains("&")
				|| filename.contains("*") || filename.contains("(")
				|| filename.contains(")") || filename.contains("{")
				|| filename.contains("}") || filename.contains("~")
				|| filename.contains("|") || filename.contains("<")
				|| filename.contains(">") || filename.contains("?"));
	}
}
