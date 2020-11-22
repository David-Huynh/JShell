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

public class Move {
	
	/**
	 * Provides the manual for how to use this command
	 * 
	 * @return The manual
	 */
	public static String getManual() {
		return "mv OLDPATH NEWPATH\n"
				+ "Move item OLDPATH to NEWPATH. Both OLDPATH and "
				+ "NEWPATH may be relative to the current \ndirectory or may be "
				+ "full paths. If NEWPATH is a"
				+ " directory, move the item into the directory.";
	}
	
	public static void performOutcome(JShell shell, String[] parameters,
			int outputType, File outputFile) {

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
