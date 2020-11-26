package driver;

import java.util.ArrayList;

/**
 * The Move command is used by the user to move a StorageUnit to a new directory.
 */
public class TransferFile extends ShellCommand {

  /**
   * Move a given StorageUnit to new location in Storage.
   * 
   * @param shell The JShell the command is to be performed on
   * @param parameters The parameters from the interpreter the command is to work with
   * @param outputType An integer representing the type of destination: 0 represents the command line,
   *        1 represents overwriting a file, and 2 represents appending to a file
   * @param outputFile If outputType is 1 or 2, this is the file we are overwriting/appending to,
   *        otherwise null
   */

  public static boolean validateNumberOfParameters(JShell shell,
      String operation, int paramLength) {
    if (paramLength != 3) {
      PrintError.reportError(shell, operation, "Invalid number of parameters");
      return false;
    }
    return true;
  }

  public static boolean validateParents(Directory toMove, Directory moveHere,
      JShell shell, String[] parameters) {
    if (toMove.checkParents(moveHere, shell)) {
      PrintError.reportError(shell, parameters[0],
          "Cannot move/copy an ancestor directory into a " + "child directory");
      return false;
    }
    return true;
  }

  public static boolean validateNames(StorageUnit toMove, Directory moveHere,
      JShell shell, String[] parameters) {
    if (toMove.checkIdenticalNames(moveHere)) {
      PrintError.reportError(shell, parameters[0],
          "Cannot move/copy '" + parameters[1] + "' into '" + parameters[2]
              + "' since a file/directory with the same "
              + "name already exists in '" + parameters[2] + "'");
      return false;
    }
    return true;
  }

  public static boolean validateFileToFile(StorageUnit toMove, File moveHere,
      JShell shell, String[] parameters) {
    if (toMove.isFile()) {
      return true;
    } else {
      PrintError.reportError(shell, parameters[0],
          "Cannot overwrite non-directory '" + parameters[2]
              + "' with directory '" + parameters[1] + "'");
      return false;
    }
  }

  public static StorageUnit determineNewStorageUnit(String dest) {
    Path moveHerePath = new Path(dest);
    String newStorageUnitName = moveHerePath
        .getPathElements()[moveHerePath.getPathElements().length - 1];
    if (dest.endsWith("/")) {
      return new Directory(newStorageUnitName, null);
    } else {
      return new File(newStorageUnitName, null, null);
    }
  }
}
