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

import java.util.ArrayList;

public class Directory extends StorageUnit {

  private ArrayList<StorageUnit> contents = new ArrayList<StorageUnit>();

  public ArrayList<StorageUnit> getDirContents() {
    return contents;
  }

  public void addFile(StorageUnit fileName) {
    contents.add(fileName);
  }

  public void delFile(StorageUnit fileName) {
    contents.remove(fileName);
  }

  public String getDirName() {
    return this.name;
  }

  public void setDirName(String name) {
    this.name = name;
  }

  public Directory getParentDir() {
    return parentDir;
  }

  public void setParentDir(Directory parent) {
    this.parentDir = parent;
  }

  // function to check if a sub-directory named dirName is under Directory
  // dir.
  // returns -1 if sub-directory named dirName is not in dir or index of
  // sub-dir if it is
  public int isSubDir(String dirName) {
    int index = -1;
    for (int i = 0; i < contents.size(); i++) {
      if (contents.get(i).getClass().getSimpleName().equals("Directory")) {
        if (contents.get(i).name.equals(dirName)) {
          index = i;
        }
      }
    }

    return index;
  }

  // function to check if a file named fileName is under Directory dir.
  // returns -1 if file named dirName is not in dir or index of file if it is
  public int containsFile(String fileName) {
    int index = -1;
    for (int i = 0; i < contents.size(); i++) {
      if (contents.get(i).getClass().getSimpleName().equals("File")) {
        if (contents.get(i).name.equals(fileName)) {
          index = i;
        }
      }
    }

    return index;
  }

  // function cycles through a path to see if the path is valid up to but not including the
  // path.size() - finalIndex - 1 index of the path, returns null if path invalid, final
  // directory/path checked by the function if valid
  public static Directory cycleDir(ArrayList<String> path, int finalIndex, Directory startDir) {
    int index = 0;
    int directoryIndex = -1;

    if (path.get(index).equals("..") || path.get(index).equals(".")) {
      if (path.get(index).equals("..")) {
        directoryIndex = -2;
      } else {
        directoryIndex = -3;
      }
    } else {
      directoryIndex = startDir.isSubDir(path.get(index));
    }

    while (directoryIndex != -1 && index < path.size() - finalIndex - 1) {
      if (directoryIndex == -2) {
        startDir = startDir.getParentDir();
      } else if (directoryIndex == -3) {

      } else {
        startDir = (Directory) startDir.getDirContents().get(directoryIndex);
      }
      index++;
      if (path.get(index).equals("..") || path.get(index).equals(".")) {
        if (path.get(index).equals("..")) {
          directoryIndex = -2;
        } else {
          directoryIndex = -3;
        }
      } else {
        directoryIndex = startDir.isSubDir(path.get(index));
      }
    }
    if (directoryIndex != -1) {
      return startDir;
    } else {
      return null;
    }
  }

  public static ArrayList<String> seperatePath(String path) {
    String[] pathArray = path.split("/");
    ArrayList<String> pathList = new ArrayList<String>();

    for (int i = 0; i < pathArray.length; i++) {
      pathList.add(pathArray[i]);
    }

    return pathList;
  }
}
