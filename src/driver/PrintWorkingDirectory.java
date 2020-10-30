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

public class PrintWorkingDirectory extends ShellCommand {

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
