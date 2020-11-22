package driver;

public class Move {
  public static void performOutcome(JShell shell, String[] parameters) {

    if (parameters.length != 3) {
      PrintError.reportError(shell, "mv", "Invalid number of parameters");
      return;
    }

    StorageUnit toMove = verifyDir(parameters[1], shell);
    StorageUnit MoveHere = verifyDir(parameters[2], shell);

    if (toMove != null && MoveHere != null) {
      if (MoveHere.getClass().getSimpleName().equals("Directory")) { // moving file/directory to another directory
        toMove.parentDir.getDirContents().remove(toMove);
        ((Directory) MoveHere).getDirContents().add(toMove);
        toMove.parentDir = (Directory) MoveHere;
      } else {
        if (toMove.getClass().getSimpleName().equals("Directory")) { // moving directory to a file (error)
          PrintError.reportError(shell, "mv",
              "Cannot overwrite file with directory");
        }
      }
    } else {
      PrintError.reportError(shell, "mv",
          "Cannot stat '" + parameters[1] + "': No such file/directory");
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
}
