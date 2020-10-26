package driver;

import java.util.ArrayList;

public class ChangeDirectory extends ShellCommand {

  public static void performOutcome(JShell shell, String[] parameters) {
    Directory currDir = shell.getCurrentDir();
    String [] directories = parameters[1].split("/");

    for (int i = 0; i < directories.length; i++) {
      if (directories[i].equals("..")) {
        currDir = currDir.getParentDir();
      } else if (directories[i].equals(".")) {
        currDir = currDir;
      } else {
        ArrayList<StorageUnit> contents = currDir.getDirContents();
        for (int j = 0; j < contents.size(); j++) {
          if (contents.get(j).name.equals(directories[i])) {
            if (contents.get(j).getClass().getSimpleName().equals("Directory")) {
              currDir.setParentDir(currDir);
              currDir = (Directory) contents.get(j);
            }
          } else {
            System.out.println("cd: no such directory: "+directories[i]);
          }
        }
      }
    }
  }
}
