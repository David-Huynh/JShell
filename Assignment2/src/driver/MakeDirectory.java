package driver;

import java.util.ArrayList;

public class MakeDirectory extends ShellCommand {

  // function to execute command for mkdir
  public static void performOutcome(JShell shell, String [] parameters) {
    Directory currDir = shell.getCurrentDir();
    String [] dir1 = {};
    String [] dir2 = {};
    if (parameters.length == 2) {
      dir1 = parameters[1].split("/");
      makeDir(currDir, dir1);
    } else if (parameters.length == 3) {
      dir1 = parameters[1].split("/");
      dir2 = parameters[2].split("/");
      makeDir(currDir, dir1);
      makeDir(currDir, dir2);
    }
  }

  // function to check for valid paths and create directory when path is valid
  private static void makeDir(Directory currDir, String [] dir) {
    for (int i = 0; i < dir.length; i++) {
      if (i+1 == dir.length) {
        if (currDir.isSubDir(dir[i]) == -1) {
          Directory newDir = new Directory();
          newDir.name = dir[i];
          currDir.addFile(newDir);
          newDir.setParentDir(currDir);
        } else {
          System.out.println("mkdir: Directory already exists: "+dir[i]);
        }
      } else {
        if (dir[i].equals("..") || dir[i].equals(".")) {
          if (dir[i].equals("..")) {
            currDir = currDir.getParentDir();
          }
        } else {
          if (currDir.isSubDir(dir[i]) == -1) {
            System.out.println("mkdir: No such directory: "+dir[i]);
            return;
          } else {
            int index = currDir.isSubDir(dir[i]);
            currDir = (Directory) currDir.getDirContents().get(index);
          }
        }
      }
    }
  }
}