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

abstract class StorageUnit { // Subclasses of this class are Files and
								// Directories

	protected String name;
	protected Directory parentDir;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Directory getParentDir() {
		return parentDir;
	}

	public void setParentDir(Directory parent) {
		this.parentDir = parent;
	}

	public static boolean hasForbidChar(String filename) {
		boolean contains = false;
		if (filename.matches(".*[/.!@#$%^&*(){}~|<>?].*")) {
			contains = true;
		}

		return contains;
	}
}