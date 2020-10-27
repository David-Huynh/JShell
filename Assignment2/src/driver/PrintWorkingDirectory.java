package driver;

public class PrintWorkingDirectory {

  public static void performOutcome(JShell shell) {
    
    Directory currDir = shell.getCurrentDir();
    String fullPath = "";
    
    if(currDir != shell.getRootDir())
    {
      fullPath = currDir.getParentDir().name + "\\" + currDir.name + fullPath;
      currDir = currDir.getParentDir();
    }
    
    while(currDir != shell.getRootDir())
    {
      fullPath = currDir.getParentDir().name + "\\" + fullPath;
      currDir = currDir.getParentDir();
    }
    
    System.out.println(fullPath);
  }

}
