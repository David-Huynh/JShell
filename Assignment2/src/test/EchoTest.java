package test;

import driver.Echo;
import driver.JShell;
import org.junit.Assert;
import org.junit.Test;

public class EchoTest {
  private JShell shell = new JShell();

  @Test
  public void testPerformOutcome(){
    //2 Arrow
    String [] test = {"echo", "\"test\"","  dsadas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("No >", 0, shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "\"Test\"", "dasdas", ">>"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals(">> After Outfile", 0, shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "Test", ">>", "dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("no \"", 0, shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "\"test\"", ">>", "dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("Normal 3 Params", 1,
        shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "\"test\"\"", ">>", "dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("Contains \" inside quotes",
        0, shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "\"test\">>", "dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals(">> attached to String",
        1, shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "\"test\"", ">>dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals(">> attached to Outfile",
        1, shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "\"test\">", ">dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals(">> Separated between String and Outfile",
        1, shell.getCurrentDir().getDirContents().size());

    shell = new JShell();
    test = new String[]{"echo", "\"test\">", ">dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals(">> Separated between String and Outfile",
        1, shell.getCurrentDir().getDirContents().size());

    //1 Arrow
    shell = new JShell();
    test = new String[]{"echo", "\"test\"", ">","dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("Normal 3 Param 1 Arrow",
        1, shell.getCurrentDir().getDirContents().size());
    shell = new JShell();
    test = new String[]{"echo", "\"test\"", "dasdas", ">"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("> After Outfile",
        0, shell.getCurrentDir().getDirContents().size());
    shell = new JShell();
    test = new String[]{"echo", "\"test\">", "dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("Attached to String",
        1, shell.getCurrentDir().getDirContents().size());
    shell = new JShell();
    test = new String[]{"echo", "\"test\"", ">dasdas"};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("Attached to Outfile",
        1, shell.getCurrentDir().getDirContents().size());
    //0 Arrow
    shell = new JShell();
    test = new String[]{"echo", "\"test\""};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("Attached to Outfile",
        0, shell.getCurrentDir().getDirContents().size());
    test = new String[]{"echo", "\"te\"st\""};
    Echo.performOutcome(shell, test);
    Assert.assertEquals("Attached to Outfile",
        0, shell.getCurrentDir().getDirContents().size());
  }
}
