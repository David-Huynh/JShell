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

import org.junit.Before;
import org.junit.Test;

import driver.*;

public class ChangeDirectoryTest {
  JShell shell;
  Directory root;
  Directory dir1;

  @Before
  public void setUp() {
    shell = new JShell();
    root = shell.getRootDir();
    dir1 = new Directory("dir1", root);
    root.addFile(dir1);
    shell.setCurrentDir(root);
  }

  @Test
  public void testPerformOutComeWithRelativePath() {
    String [] parameters = {"cd", "dir1"};
    driver.ChangeDirectory.performOutcome(shell, parameters, 0, null);
    assertEquals("dir1", shell.getCurrentDir().getName());
    assertEquals("/", shell.getCurrentDir().getParentDir().getName());
  }

  @Test
  public void testPerformOutComeWithAbsPath() {
    String [] parameters = {"cd", "/dir1"};
    driver.ChangeDirectory.performOutcome(shell, parameters, 0, null);
    assertEquals("dir1", shell.getCurrentDir().getName());
    assertEquals("/", shell.getCurrentDir().getParentDir().getName());
  }

  @Test
  public void testPerformOutComeWithDotPath() {
    String [] parameters = {"cd", "./dir1/.."};
    driver.ChangeDirectory.performOutcome(shell, parameters, 0, null);
    assertEquals("/", shell.getCurrentDir().getName());
    assertEquals("/", shell.getCurrentDir().getParentDir().getName());
  }

}
