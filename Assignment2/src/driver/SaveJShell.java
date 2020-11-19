package driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveJShell extends ShellCommand{
  public static String getManual() {
    return "saveJShell localFilePath\n"
        + "The above command will interact with your real file system on your computer.\n"
        + "Saves the current working session of the shell"
        + "so that it can be loaded in a future session";
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
