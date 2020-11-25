package test;

import static org.junit.Assert.assertEquals;

import driver.SaveJShell;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import driver.JShell;

public class SaveJShellTest {
  JShell shell;
  @Before
  public void setUp() {
    shell = new JShell();
  }
  @Test
  public void testGetManual() {
    String manual;
    manual = SaveJShell.getManual();
    assertEquals(manual, "saveJShell localFilePath\n"
        + "The above command will interact with your real file "
        + "system on your computer.\n"
        + "Saves the current working session of the shell"
        + "so that it can be loaded in a future session");
  }
  @Test
  public void testPerformOutcome(){
    SaveJShell.performOutcome(shell, new String[]{"saveJShell"," ./test.txt"},
        0, null);
    File f = new File("./test.txt");
    assertEquals(f.isFile(), true);
  }
}
