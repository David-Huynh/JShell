package driver;

import java.util.ArrayList;

public class ListFiles {
  
  public static void performOutcome(JShell shell, String parameter)
  {
    String[] parameters = parameter.split(" "); //Split parameter into array of parameters, 
                                                //with each individual element being an individual file/directory
    
    if(parameter.equals("")) //Case 1: Called with no parameters, list files of current directory
    {
      List(shell.getCurrentDir());
    }
    
  }
  
  public static void List(Directory currentDirectory) //Function used to print all files in directory
  {
    ArrayList<StorageUnit> fileList = currentDirectory.getDirContents();
    
    for(int i = 0; i < fileList.size();i++)
    {
      System.out.println(fileList.get(i));
    }
  }

}
