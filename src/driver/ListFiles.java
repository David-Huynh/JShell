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

/**
 * The ListFiles command is used by the user to print the contents of a specific directory in the
 * file system.
 */

public class ListFiles extends ShellCommand {

  /**
   * Provides the manual for how to use this command
   * 
   * @return The manual
   */
  public static String getManual() {
    return "ls [PATH ...] \nIf no paths are given, print the contents "
        + "(file or directory) of the current \ndirectory, with a new "
        + "line following each of the content (file or directory). \n"
        + "Otherwise, for each path p, the order listed: \n    - If p "
        + "specifies a file, print p \n    - If p specifies a "
        + "directory, print p, a colon, then the contents of that \n"
        + "      directory, then an extra new line. \n    - If p does "
        + "not exist, print a suitable message.  ";
  }

  /**
   * Tell the JShell to list all the contents of a specific directory in the file system.
   * 
   * @param shell The JShell the command is to be performed on
   * @param parameters The parameters from the interpreter the command is to work with
   */
  public static void performOutcome(JShell shell, String[] parameters) {

    Directory currDir = shell.getCurrentDir();
    ArrayList<StorageUnit> fileList = currDir.getDirContents();
    Path path;

    if (parameters.length == 1) {// Case 1: no parameters, list files of
                                 // current directory
      list(shell, fileList);
    } else {
      for (int i = 1; i < parameters.length; i++) {// Case 2: >= 1
                                                   // parameters go
                                                   // through all
        path = new Path(parameters[i]); // separate path into its
                                        // directories
        if (path.isAbsolute()) { // path is absolute
          currDir = shell.getRootDir();
        }

        currDir = path.cyclePath(0, currDir, shell);

        if (currDir == null) {
          PrintError.reportError(shell, "ls", "Cannot access '" + parameters[i]
              + "', no such file or directory.");
          return;
        } else {
          StorageUnit listThis = path.determineFinalElement(currDir);
          if (listThis == null) {
            PrintError.reportError(shell, "ls", "Cannot access '"
                + path.getPath() + "', no such file or directory.");
          } else if (listThis.getClass().getSimpleName().equals("File")) {
            shell.println(path.getPath());
          } else {
            shell.println(parameters[i] + ":");
            list(shell, ((Directory) listThis).getDirContents());
          }
          currDir = shell.getCurrentDir(); // reset for next parameter
        }
      }
    }
  }

  /**
   * Prints all StorageUnit names in an ArrayList of StorageUnits.
   * 
   * @param shell The JShell to print the contents on
   * @param fileList The ArrayList of StorageUnits whose names are to be printed
   */
  public static void list(JShell shell, ArrayList<StorageUnit> fileList) {
    // Function used to print all files in directory
    for (int i = 0; i < fileList.size(); i++) {
      shell.println(fileList.get(i).name);
    }
  }
}
