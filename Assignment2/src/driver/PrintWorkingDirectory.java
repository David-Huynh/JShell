package driver;

public class PrintWorkingDirectory {

  public static void performOutcome(JShell shell) {
    
    Directory currDir = shell.getCurrentDir();
    String fullPath = "";
    
    while(currDir != shell.getRootDir())
    {
      fullPath = currDir.getParentDir().name + "\\" + currDir.name + fullPath;
      currDir = currDir.getParentDir();
    }
    
    System.out.println(currDir.name + fullPath);
  }

}
