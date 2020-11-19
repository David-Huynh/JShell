package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadJShell extends ShellCommand{
  public static String getManual() {
    return "";
  }
  public static void performOutcome(JShell shell, String[] parameters) {
    try{
      ObjectInputStream in = new ObjectInputStream (new FileInputStream(parameters[1]));
      JShell newShell = (JShell) in.readObject();
      shell.updateShell(newShell);
      in.close();
    }catch (IOException e){
      System.out.println("File not found");
    }catch (ClassNotFoundException e){
      System.err.println(e.getMessage());
    }
  }

}
