package driver;

import java.util.ArrayList;

public class MakeDirectory extends ShellCommand {

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

  //private static int isSubDir(Directory dir, String dirName) {
    //int valid = 0;

    //for (StorageUnit)
  //}

  private static void makeDir(Directory currDir, String [] dir) {
    for (int i = 0; i < dir.length; i++) {
      if (i+1 == dir.length) {
        //if () {

        //}
        Directory newDir = new Directory();
        newDir.name = dir[i];
        currDir.addFile(newDir);
        newDir.setParentDir(currDir);
      } else {
        if (dir[i].equals("..") || dir[i].equals(".")) {
          if (dir[i].equals("..")) {
            currDir = currDir.getParentDir();
          }
        } else {
          ArrayList<StorageUnit> contents = currDir.getDirContents();
          int valid = 0;
          for (StorageUnit content: contents) {
            if (content.name.equals(dir[i])) {
              if (content.getClass().getSimpleName().equals("Directory")) {
                currDir = (Directory) content;
                valid = 1;
                break;
              }
            }
          }
          if (valid == 0) {
            System.out.println("mkdir: No such directory: "+dir[i]);
            return;
          }
        }
      }
    }
  }
}