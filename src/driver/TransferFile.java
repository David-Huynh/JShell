package driver;

public class TransferFile extends ShellCommand {

  public static void performOutcome(JShell shell, String[] parameters,
      int outputType, File outputFile) {

	  if (outputType != 0) {
			PrintError.reportError(shell, "cd",
					"This command does not produce stdout.");
			return;
		}
    if (parameters.length != 3) {
      PrintError.reportError(shell, "mv", "Invalid number of parameters");
      return;
    }

    String operation = parameters[0];

    StorageUnit toMove = verifyDir(parameters[1], shell);
    StorageUnit moveHere = verifyDir(parameters[2], shell);

    if (toMove == null) { // Case 1: toMove does not exist
      PrintError.reportError(shell, "mv",
          "Cannot stat '" + parameters[1] + "': No such file or directory");
      return;
    }
    if (moveHere != null) { // moveHere and toMove are valid StorageUnits in the JShell
      if (moveHere.isDirectory() && toMove.isDirectory()) { // Case 2: both moveHere and toMove are directories
        if (moveHere.checkParents((Directory) toMove, shell)) { // check if moveHere is an ancestor of toMove
          PrintError.reportError(shell, operation,
              "Cannot move/copy an ancestor directory into a child directory");
          return;
        }
        if (toMove.checkIdenticalNames((Directory) moveHere)) { // check if moveHere has a StorageUnit with the same name as toMove
          PrintError.reportError(shell, operation, "Cannot move/copy '"
              + parameters[1] + "' into '" + parameters[2]
              + "' since a file/directory with the same name already exists in '"
              + parameters[2] + "'");
          return;
        }
        determineOperation(operation, toMove, moveHere, operation);
      }
      if (moveHere.isDirectory() && toMove.isFile()) { // Case 3: moveHere is a directory, toMove is a file
        if (toMove.checkIdenticalNames((Directory) moveHere)) { // check if moveHere has a StorageUnit with the same name as toMove
          PrintError.reportError(shell, operation, "Cannot move/copy '"
              + parameters[1] + "' into '" + parameters[2]
              + "' since a file/directory with the same name already exists in '"
              + parameters[2] + "'");
          return;
        }
        determineOperation(operation, toMove, moveHere, operation);
      }
      if (moveHere.isFile() && toMove.isFile()) { // Case 4: moveHere is a file, toMove is a file

        determineOperation("ow", toMove, moveHere, operation);
      }
    }
  }

  public static StorageUnit verifyDir(String parameter, JShell shell) {

    Path pathMove = new Path(parameter);
    Directory startDir;

    if (pathMove.isAbsolute()) {
      startDir = shell.getRootDir();
    } else {
      startDir = shell.getCurrentDir();
    }

    Directory parentOfFinal = pathMove.cyclePath(0, startDir, shell);
    StorageUnit finalItem = pathMove.determineFinalElement(parentOfFinal);
    return finalItem;
  }

  private static void determineOperation(String operation, StorageUnit toMove,
      StorageUnit moveHere, String userCall) {

    if (operation.equals("mv")) { // move
      move(toMove, (Directory) moveHere);
    }
    if (operation.equals("cp")) { // copy
      Copy.copy(toMove, (Directory) moveHere);
    }
    if (operation.equals("rn")) { // rename
      rename(toMove, moveHere);
    }
    if (operation.equals("ow")) { // overwrite
      overwrite((File) toMove, (File) moveHere, userCall);
    }
    if (operation.equals("nf")) { // new file
      newFile(toMove, moveHere);
    }
  }

  private static void newFile(StorageUnit toMove, StorageUnit moveHere) {

  }

  private static void overwrite(File toMove, File moveHere, String userCall) {
    if (userCall.equals("mv")) {
      moveHere.getParentDir().getDirContents().add(toMove);
      toMove.parentDir = moveHere.getParentDir();
      moveHere.getParentDir().getDirContents().remove(moveHere);
    } else {
      moveHere.overwrite(toMove.getContents());
    }
  }

  private static void rename(StorageUnit toMove, StorageUnit moveHere) {

  }

  private static void move(StorageUnit toMove, Directory moveHere) {
    toMove.getParentDir().getDirContents().remove(toMove);
    moveHere.getDirContents().add(toMove);
    toMove.setParentDir(moveHere);
  }
}


