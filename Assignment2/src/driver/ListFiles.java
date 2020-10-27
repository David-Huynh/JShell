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

import java.util.ArrayList;

public class ListFiles extends ShellCommand{
  
  public static void performOutcome(JShell shell, String[] parameters)
  {
    
    if(parameters.length == 1) //Case 1: Called with no parameters, list files of current directory
    {
      List(shell.getCurrentDir());
    }
    else
    {
      for(int i = 1; i < parameters.length; i++)
      {
        //Check if list element is directory
          //Print directory name + ':'
          //Print directory contents
        
        //Check if list element is file
          //Print file name
      }
    }
  }
  
  public static void List(Directory currentDirectory) //Function used to print all files in directory
  {
    ArrayList<StorageUnit> fileList = currentDirectory.getDirContents();
    
    for(int i = 0; i < fileList.size();i++)
    {
      System.out.println(fileList.get(i).name);
    }
  }

}
