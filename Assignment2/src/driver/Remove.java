package driver;

public class Remove extends ShellCommand {

  public static void performOutcome(JShell shell, String[] parameters) {

    if (parameters.length != 2) {
      PrintError.reportError(shell, "rm", "Invalid number of parameters");
      return;
    }

    Path toDelete = new Path(parameters[1]);
    Directory startDir;

    if (toDelete.isAbsolute()) {
      startDir = shell.getRootDir();
    } else {
      startDir = shell.getCurrentDir();
    }

    Directory parentOfDeleted = toDelete.cyclePath(0, startDir, shell);

    if (parentOfDeleted == null) {
      PrintError.reportError(shell, "rm",
          "Cannot access '" + parameters[1] + "', no such file or directory.");
      return;
    }

    int indexDelete = toDelete.determineFinalElement(parentOfDeleted);
    if (indexDelete == -1) {
      PrintError.reportError(shell, "rm",
          "Cannot access '" + parameters[1] + "', no such file or directory.");
      return;
    } else if (indexDelete == -2) {
      if(parentOfDeleted.getParentDir().checkParents(shell)) {
        PrintError.reportError(shell, "rm",
            "Cannot remove current working directory or any of its ancestors");
        return;
      }
      parentOfDeleted.getParentDir().getParentDir().getDirContents().remove(parentOfDeleted.getParentDir());
    } else if (indexDelete == -3) {
      if(parentOfDeleted.checkParents(shell)) {
        PrintError.reportError(shell, "rm",
            "Cannot remove current working directory or any of its ancestors");
        return;
      }
      parentOfDeleted.getParentDir().getDirContents().remove(parentOfDeleted);
    } else {
      if(parentOfDeleted.getDirContents().get(indexDelete).checkParents(shell)) {
        PrintError.reportError(shell, "rm",
            "Cannot remove current working directory or any of its ancestors");
        return;
      }
      parentOfDeleted.getDirContents().remove(indexDelete);
    }
  }
}
