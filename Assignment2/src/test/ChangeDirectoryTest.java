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
