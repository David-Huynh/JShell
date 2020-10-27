package driver;

import java.util.ArrayList;

public class ChangeDirectory extends ShellCommand {

  // function to execute command for cd
  public static void performOutcome(JShell shell, String[] parameters) {
    Directory currDir = shell.getCurrentDir();
    String [] subDir = {};
    if (parameters.length > 1) {
      subDir = parameters[1].split("/");
    }

    for (int i = 0; i < subDir.length; i++) {
      if (subDir[i].equals("..") || subDir[i].equals(".")) {
        if (subDir[i].equals("..")) {
          currDir = currDir.getParentDir();
          shell.setCurrentDir(currDir);
        }
      } else {
        if (currDir.isSubDir(subDir[i]) == -1) {
          System.out.println("cd: no such directory: "+subDir[i]);
          return;
        } else {
          int index = currDir.isSubDir(subDir[i]);
          currDir = (Directory) currDir.getDirContents().get(index);
          shell.setCurrentDir(currDir);
        }
      }
    }
  }
}
