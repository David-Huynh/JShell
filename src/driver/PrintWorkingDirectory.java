package driver;

import java.util.Stack;

public class PrintWorkingDirectory {

  public static void performOutcome(JShell shell) {
    
    //To print the absolute path, we need to implement a directory stack 
    
    //Stack<String> directoryStack = shell.getAbsolutePath();
    
    String fullPath = "";
    
    Stack<String> directoryStack = null; // remove when directoryStack is actually implemented
    
    for(int i = 0; i < directoryStack.size(); i++)
    {
      fullPath = directoryStack.get(i) + "\\" + fullPath;
    }
    
    System.out.println(fullPath);
  }

}
