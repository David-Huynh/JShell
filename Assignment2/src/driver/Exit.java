package driver;

public class Exit {
  public static String exit(JShell shell){
    shell.exit();
    return "Shell has terminated";
  }
}
