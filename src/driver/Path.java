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
 * A path is a collection of directories listed in succession separated by delimiter '/' the final
 * element in a path may be either a file or folder
 * 
 */

public class Path {

  /** A string representation of the path to be checked */
  private String path;
  /**
   * The path stored as an array of strings, with the name of each directory/file in the path being
   * its own element in the array
   */
  private String[] pathElements;
  /** Marks whether path is absolute (from root) or relative to the current directory */
  private boolean absolute;

  /**
   * Initializes a Path variable with the given string. Note that if the path is absolute, index 0 of
   * the array will be the empty string, and if path only contains forward slashes,
   * pathElements.length == 0
   * 
   * @param path The string representation of the path to be stored
   */
  public Path(String path) {
    this.setPath(path);
    this.pathElements = (path.split("/"));
    this.absolute = this.determineAbsolute();
  }

  /**
   * Public getter method for the string path
   * 
   * @return The string representation of the path of a path variable
   */
  public String getPath() {
    return path;
  }

  /**
   * Public getter method for pathElements
   * 
   * @return The array of strings, pathElements of a path variable
   */
  public String[] getPathElements() {
    return pathElements;
  }

  /**
   * Public getter method for the boolean absolute
   * 
   * @return The boolean absolute of a path variable
   */
  public boolean isAbsolute() {
    return absolute;
  }

  /**
   * Changes the path that the path variable refers to
   * 
   * @param path The string representation of the path to be referred to
   */
  public void setPath(String path) {
    this.path = path;
    this.pathElements = (path.split("/"));
    this.absolute = this.determineAbsolute();
  }

  /**
   * Cycles through a path to see if the path is valid up to, but not including the path.size() -
   * finalIndex - 1 index of the path
   * 
   * Precondition: startDir is a directory in the path
   * 
   * The index of startDir.name in this.path.getPathElements() < finalIndex
   * 
   * @param path A string representation of the path to be checked
   * @param finalIndex The end index of the path that will not be checked
   * @param startDir The start directory of the path
   * @param shell The JShell to perform in
   * @return null, if path is invalid, final directory checked by the function if valid
   */
  public Directory cyclePath(int finalIndex, Directory startDir, JShell shell) {
    String[] pathElements = this.getPathElements();
    int index = 0;
    int directoryIndex = -1;

    if (this.path.equals("/")) {//path is root directory
      return shell.getRootDir();
    }
    if (pathElements.length == 0) { //path is invalid
      return null;
    }
    if (this.isAbsolute()) { //skip empty string
      index++;
    }

    directoryIndex = determinePathElement(startDir, index); 

    while (directoryIndex != -1
        && index < pathElements.length - finalIndex - 1) {
      if (directoryIndex == -2) {
        startDir = startDir.getParentDir();
      } else if (directoryIndex == -3) {
      } else {
        startDir = (Directory) startDir.getDirContents().get(directoryIndex);
      }
      index++;
      directoryIndex = determinePathElement(startDir, index);
    }
    if (startDir != shell.getRootDir()) {
      directoryIndex = startDir.getParentDir().isSubDir(startDir.getName());
    }
    if (directoryIndex != -1 || pathElements.length == 1) {
      return startDir;
    } else {
      return null;
    }
  }

  /**
   * Determines whether the given index of this.path.getPathElements() is valid in the given directory
   * 
   * @param directory The directory where the element of the path is checked in
   * @param index The index of the element in path that is checked for validity
   */
  public int determinePathElement(Directory directory, int index) {
    int directoryIndex = -1;
    String[] pathElements = this.getPathElements();

    if (pathElements[index].equals("..") || pathElements[index].equals(".")) {
      if (pathElements[index].equals("..")) {
        directoryIndex = -2;
      } else {
        directoryIndex = -3;
      }
    } else {
      directoryIndex = directory.isSubDir(pathElements[index]);
    }

    return directoryIndex;
  }

  public boolean determineAbsolute() {
    if (this.path.startsWith("/")) {
      return true;
    }
    return false;
  }

  /**
   * Determines whether the final element is a valid file or directory
   * 
   * Precondition: Path is valid up to but not including the final element
   * 
   * @param directory The directory where the final element should be stored
   * @return indicator, which is -1, if final element doesn't exist, -3 if final element is
   *         directory.getParentDir(), -2 if final element is directory, otherwise the index of the
   *         final element within directory.getDirContents()
   */
  public int determineFinalElement(Directory directory) {
    String[] pathElements = this.getPathElements();
    int finalIndex = pathElements.length - 1;
    int indicator;

    indicator = determinePathElement(directory, finalIndex);
    if (indicator != -1) {
      return indicator;
    } else {
      indicator = directory.containsFile(pathElements[finalIndex]);
    }
    return indicator;
  }
}
