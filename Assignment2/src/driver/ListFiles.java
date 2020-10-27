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
