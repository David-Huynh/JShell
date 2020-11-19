package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadJShell extends ShellCommand{
  public static String getManual() {
    return "loadJShell localFilePath\n"
        + "Loads a previously saved JShell session onto a fresh JShell sessions.\n"
        + "This command only works on fresh JShell sessions.";
  }
  public static void performOutcome(JShell shell, String[] parameters) {
    if (shell.getComHis().size() == 1){
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
    }else {
      PrintError.reportError(shell,"loadJShell: there are unsaved changes in this session,"
          + "\nstart a fresh shell to load a previously saved session.");
    }
  }

}
