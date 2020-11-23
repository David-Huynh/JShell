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

/**
 * A Directory is a type of StorageUnit (similar to a folder in a computer's file system) that holds
 * a collection of other StorageUnits.
 */

public class Directory extends StorageUnit implements Serializable {

  /**
   * An ArrayList containing all the contents of the Directory, similar to the contents of a
   * computer's folder
   */
  private ArrayList<StorageUnit> contents = new ArrayList<StorageUnit>();

  /**
   * Initializes a Directory with the given name in a given location
   * 
   * @param name The name to be given to the Directory
   * @param parentDir The location the Directory will live in
   */
  public Directory(String name, Directory parentDir) {
    this.name = name;
    this.parentDir = parentDir;
  }

  /**
   * Public getter method for the Directory's contents
   * 
   * @return The Directory's contents
   */
  public ArrayList<StorageUnit> getDirContents() {
    return contents;
  }

  /**
   * Adds a StorageUnit to the Directory
   * 
   * @param fileName The StorageUnit to be added
   */
  public void addFile(StorageUnit fileName) {
    contents.add(fileName);
  }

  /**
   * Deletes a StorageUnit from the Directory
   * 
   * @param fileName The StorageUnit to be added
   */
  public void delFile(StorageUnit fileName) {
    contents.remove(fileName);
  }

  /**
   * Gets a StorageUnit of a given index in the Directory
   * 
   * @param index The desired index
   * @return The StorageUnit in that index
   */
  StorageUnit getFile(int index) {
    return contents.get(index);
  }

  /**
   * Checks if a Directory with a specific name is in the Directory's contents.
   * 
   * @param dirName The name of the Directory to be checked if it is in the Directory
   * @return -1 if sub-directory named dirName is not in the Directory or index of the sub-directory
   *         if it is
   */
  public int isSubDir(String dirName) {
    int index = -1;
    for (int i = 0; i < contents.size(); i++) {
      // checks if the element is of type Directory
      if (contents.get(i).isDirectory()) {
        if (contents.get(i).name.equals(dirName)) {
          index = i;
        }
      }
    }
    return index;
  }

  /**
   * Checks if a File with a specific name is in the Directory's contents.
   * 
   * @param fileName The name of the File to be checked if it is in the Directory
   * @return -1 if file named fileName is not in the Directory or index of the file if it is
   */
  public int containsFile(String fileName) {
    int index = -1;
    for (int i = 0; i < contents.size(); i++) {
      // checks if the element is of type File
      if (contents.get(i).isFile()) {
        if (contents.get(i).name.equals(fileName)) {
          index = i;
        }
      }
    }
    return index;
  }

  /**
   * Returns an identical directory that can be modified independently from this
   * 
   * @param parentDir The directory where the cloned directory will be located
   * @return The cloned directory
   */
  public Directory clone(Directory parentDir) {
    Directory clonedDir;
    clonedDir = new Directory(this.name, parentDir);
    clonedDir.contents = new ArrayList<StorageUnit>();

    for (StorageUnit x : this.contents) {
      if (x.isDirectory()) {
        Directory y = ((Directory) x).clone(clonedDir);
        clonedDir.contents.add(y);
      } else {
        File z = ((File) x).clone(clonedDir);
        clonedDir.contents.add(z);
      }
    }
    return clonedDir;
  }
}
