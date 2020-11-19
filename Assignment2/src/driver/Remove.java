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
      parentOfDeleted.getParentDir().getParentDir().getDirContents().remove(parentOfDeleted.getParentDir());
    } else if (indexDelete == -3) {
      parentOfDeleted.getParentDir().getDirContents().remove(parentOfDeleted);
    } else {
      parentOfDeleted.getDirContents().remove(indexDelete);
    }
  }
}
