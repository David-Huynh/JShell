package driver;

public class Copy extends ShellCommand {

  public static boolean producesStdOut() {
    return false;
  }

  public static String getManual() {
    return "mv OLDPATH NEWPATH\n"
        + "Copy item OLDPATH to NEWPATH. Both OLD-PATH and NEWPATH may " + "be "
        + "relative to \nthe current directory or may be " + "full paths. If "
        + "NEWPATH is adirectory, copy \nthe item into the directory.";
  }

  public static void performOutcome(JShell shell, String[] parameters,
      int outputType, File outputFile) {

    if (!TransferFile.validateNumberOfParameters(shell, parameters[0],
        parameters.length)) {
      return;
    }

    StorageUnit toCopy = new Path(parameters[1]).verifyPath(shell, false);
    StorageUnit copyHere = new Path(parameters[2]).verifyPath(shell, false);
    Directory copyHereParent =
        (Directory) new Path(parameters[2]).verifyPath(shell, true);

    if (toCopy == null) {
      PrintError.reportError(shell, "mv",
          "Cannot stat '" + parameters[1] + "': No such file or directory");
      return;
    }
    if (copyHere != null) {
      executeExistingPath(copyHere, toCopy, shell, parameters);
    } else if (copyHereParent != null) {
      executeNewPath(copyHere, toCopy, copyHereParent, shell, parameters);
    } else {
      PrintError.reportError(shell, parameters[0],
          "Cannot stat '" + parameters[2] + "': Path is invalid.");
    }

  }

  private static void executeExistingPath(StorageUnit copyHere,
      StorageUnit toCopy, JShell shell, String[] parameters) {
    if (copyHere.isDirectory()) {
      if (!TransferFile.validateNames(toCopy, (Directory) copyHere, shell,
          parameters)) {
        return;
      } else if (toCopy.isDirectory() && TransferFile.validateParents(
          (Directory) toCopy, (Directory) copyHere, shell, parameters)) {
        copy(toCopy, (Directory) copyHere);
      } else if (toCopy.isFile()) {
        copy(toCopy, (Directory) copyHere);
      } else {
        return;
      }
    } else if (copyHere.isFile()) {
      if (!TransferFile.validateFileToFile(copyHere, (File) copyHere, shell,
          parameters)) {
        return;
      }
      copyAndOverwriteFile((File) toCopy, (File) copyHere);
    }
  }

  private static void executeNewPath(StorageUnit copyHere, StorageUnit toCopy,
      Directory copyHereParent, JShell shell, String[] parameters) {
    StorageUnit copyHereNew =
        TransferFile.determineNewStorageUnit(parameters[2]);
    Path destPath = new Path(parameters[2]);
    String newStorageUnitName =
        destPath.getPathElements()[destPath.getPathElements().length - 1];
    if (toCopy.isDirectory() && TransferFile.validateParents((Directory) toCopy,
        copyHereParent, shell, parameters)) {
      copyAndOverwriteDir(toCopy, copyHereParent, newStorageUnitName);
    } else if (toCopy.isFile() && copyHereNew.isFile()) {
      copyAndCreateFile((File) toCopy, (File) copyHereNew, copyHereParent);
    } else {
      PrintError.reportError(shell, parameters[0], "Cannot copy file '"
          + parameters[1] + "' into a directory that doesn't exist.");
    }
  }

  public static void copy(StorageUnit toCopy, Directory copyHere) {
    StorageUnit toAdd = toCopy.clone((Directory) copyHere);
    copyHere.getDirContents().add(toAdd);
  }

  private static void copyAndOverwriteDir(StorageUnit toCopy,
      Directory copyHereParent, String newName) {

    copy(toCopy, copyHereParent);
    toCopy.setName(newName);
  }

  public static void copyAndOverwriteFile(File toCopy, File copyHere) {
    copyHere.overwrite(toCopy.getContents());
  }

  private static void copyAndCreateFile(File toCopy, File copyHereNew,
      Directory copyHereParent) {

    copyHereParent.addFile(
        new File(copyHereNew.getName(), toCopy.getContents(), copyHereParent));
  }
}
