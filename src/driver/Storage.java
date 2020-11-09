package driver;

/**
 * Storage is similar to a computer's file system, or root directory of a Unix
 * shell. i.e., it is the Directory in which all existing JShells work in.
 */

public class Storage {

	/** The root directory of the storage system */
	private Directory root;

	/** The one and only reference to the Storage system */
	private static Storage onlyReference = null;

	/**
	 * Initializer for Storage, made private as we cannot allow multiple to be
	 * created
	 */
	private Storage(String name, Directory parentDir) {
		this.root = new Directory("/", null);
		this.root.setParentDir(this.root);
		// parent of root directory is always the root itself
	}

	/**
	 * Public getter method for the root directory.
	 * 
	 * @return The root directory of the storage system
	 */
	public Directory getRoot() {
		return this.root;
	}

	/**
	 * Get the only instance of Storage, creates it it if it doesn't exist
	 * 
	 * @return A newly created Storage if there is none yet, and returns the
	 *         only instance of it if there already is one
	 */
	public static Storage createNewStorage() {
		if (onlyReference == null) {
			onlyReference = new Storage("/", null);
		}
		// Singleton Design Pattern is used to ensure only ONE Storage is
		// created
		return onlyReference;
	}
}
