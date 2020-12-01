// **********************************************************
// Assignment2:
// Student1: Collin Chan
// UTORID user_name: chancol7
// UT Student #: 1006200889
// Author: Collin Chan
//
// Student2: Jeff He
// UTORID user_name: Hejeff2
// UT Student #: 1006398783
// Author: Jeff He
//
// Student3: Nevin Wong
// UTORID user_name: wongnevi
// UT Student #: 1005391434
// Author: Nevin Wong
//
// Student4: David Huynh
// UTORID user_name: huynhd12
// UT Student #: 1005991937
// Author: David Huynh
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.*;

public class SearchTest {
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
    root.addFile(dir1);
    stdOutFile = new File("file", "", null);
    System.setOut(new PrintStream(consoleStreamCaptor));
  }

  @Test
  public void testPerformOutComeForExistingDir() {
    String [] parameters = {"search", "/", "-type", "d", "-name", "\"dir1\""};
    driver.Search.performOutcome(shell, parameters, 1, stdOutFile);
    assertEquals("/dir1", stdOutFile.getContents());
  }

  @Test
  public void testPerformOutComeForExistingFile() {
    file1 = new File("file1", "123", dir1);
    dir1.addFile(file1);
    String [] parameters = {"search", "/", "-type", "f", "-name", "\"file1\""};
    driver.Search.performOutcome(shell, parameters, 1, stdOutFile);
    assertEquals("dir1/file1", stdOutFile.getContents());
  }

}
