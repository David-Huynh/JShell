package driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveJShell extends ShellCommand{
  public static String getManual() {
    return "";
  }
  public static void performOutcome(JShell shell, String[] parameters) {
    try{
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(parameters[1]));
      out.writeObject(shell);
      out.close();
    }catch (IOException e){
      System.err.println(e.getMessage());
    }
  }

}
