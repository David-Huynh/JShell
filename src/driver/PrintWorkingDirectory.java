package driver;

public class PrintWorkingDirectory {

  public static void performOutcome(JShell shell) {
    
    Directory currDir = shell.getCurrentDir(); //get current directory
    String fullPath = "";
    
    if(currDir != shell.getRootDir())
    {
      fullPath = currDir.getParentDir().name + "\\" + currDir.name + fullPath; //append name of parent directory to current directory
      currDir = currDir.getParentDir();
    
        while(currDir != shell.getRootDir())
        {
          fullPath = currDir.getParentDir().name + "\\" + fullPath; //keep appending parent directory until we hit root
          currDir = currDir.getParentDir();
        }
        
        System.out.println(fullPath); //print absolute path
    }
    else
    {
      System.out.println(shell.getRootDir().name); //print root directory name, since above prints nothing if currDir = root
    }
  }

}
