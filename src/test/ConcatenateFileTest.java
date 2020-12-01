package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

import driver.*;

public class ConcatenateFileTest {
  JShell shell;
  Directory root;
  Directory dir1;
  File file1;
  File stdOutFile;

  /** Used to test print statements */
  private final PrintStream printed = System.out;
  private final ByteArrayOutputStream consoleStreamCaptor =
      new ByteArrayOutputStream();

  @Before
  public void setUp() {
    shell = new JShell();
    root = shell.getRootDir();
    dir1 = new Directory("dir1", root);
    file1 = new File("file1", "123", dir1);
    stdOutFile = new File("file", "", null);
    System.setOut(new PrintStream(consoleStreamCaptor));
  }

  @Test
  public void testGetManual() {
    assertEquals("cat FILE1 [FILE2 ...] \n"
        + "Display the contents of FILE1 and other files "
        + "(i.e. File2 ...) concatenated in \n"
        + "the shell. You may want to "
        + "use three line breaks to separate "
        + "the contents of one file \n" + "from the other file.",
        driver.ConcatenateFile.getManual());
  }

  @Test
  public void testPerformOutComeWithValidRelPath() {
    root.addFile(dir1);
    dir1.addFile(file1);
    String [] parameters = {"cat", "dir1/file1"};
    driver.ConcatenateFile.performOutcome(shell, parameters, 1, stdOutFile);
    assertEquals("123", stdOutFile.getContents());
  }

  @Test
  public void testPerformOutComeWithValidAbsPath() {
    root.addFile(dir1);
    dir1.addFile(file1);
    String [] parameters = {"cat", "/dir1/file1"};
    driver.ConcatenateFile.performOutcome(shell, parameters, 1, stdOutFile);
    assertEquals("123", stdOutFile.getContents());
  }

  @Test
  public void testPerformOutComeWithMultiplePaths() {
    root.addFile(dir1);
    dir1.addFile(file1);
    String [] parameters = {"cat", "/dir1/file1", "../dir1/file1"};
    driver.ConcatenateFile.performOutcome(shell, parameters, 1, stdOutFile);
    assertEquals("123\n\n\n\n123", stdOutFile.getContents());
  }
}
