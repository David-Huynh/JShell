package driver;

import java.util.ArrayList;

public class ChangeDirectory extends ShellCommand {

  public static void performOutcome(JShell shell, String[] parameters) {
    Directory currDir = shell.getCurrentDir();
    String [] directories = {};
    if (parameters.length > 1) {
      directories = parameters[1].split("/");
    }

    for (int i = 0; i < directories.length; i++) {
      if (directories[i].equals("..") || directories[i].equals(".")) {
        if (directories[i].equals("..")) {
          shell.setCurrentDir(currDir.getParentDir());
        }
      } else {
        ArrayList<StorageUnit> contents = currDir.getDirContents();
        int valid = 0;
        for (int j = 0; j < contents.size(); j++) {
          if (contents.get(j).name.equals(directories[i])) {
            if (contents.get(j).getClass().getSimpleName().equals("Directory")) {
              currDir = (Directory) contents.get(j);
              shell.setCurrentDir(currDir);
              valid = 1;
              break;
            }
          }
        }
        if (valid == 0) {
          System.out.println("cd: no such directory: "+directories[i]);
          return;
        }
      }
    }
  }
}
