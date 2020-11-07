package driver;

/**
 * Storage is similar to a computer's file system, or root directory of a Unix
 * shell. i.e., it is the Directory in which all existing JShells work in.
 */

public class Storage extends Directory {

	/** The one and only reference to the Storage system */
	private static Storage onlyReference = null;

	/**
	 * Initializer for Storage, made private as we cannot allow multiple to be
	 * created
	 */
	private Storage(String name, Directory parentDir) {
		super(name, parentDir);
	}

	/**
	 * Get the only instance of Storage, creates it it if it doesn't exist
	 * 
	 * @return A newly created Storage if there is none yet, and returns the
	 * only instance of it if there already is one
	 */
	public static Storage createNewStorage() {
		if (onlyReference == null) {
			onlyReference = new Storage("/", null);
			onlyReference.setParentDir(onlyReference);
			// parent of root directory is always the root itself
		}
		// Singleton Design Pattern is used to ensure only ONE Storage is
		// created
		return onlyReference;
	}
}
