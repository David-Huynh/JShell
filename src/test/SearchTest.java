package test;

import static org.junit.Assert.*;

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

  @Before
  public void setUp() {
    shell = new JShell();
    root = shell.getRootDir();
    dir1 = new Directory("dir1", root);
    file1 = new File("file1", "123", dir1);
  }

  @Test
  public void testPerformOutComeForExistingDir() {
    String [] parameters = {"search", "/", "-type", "d", "-name", "\"dir1\""};
    String name = parameters[5]
        .replaceAll("^\"+|\"+$", "");
    driver.Search.recSearch(root, name, parameters[3]);
    assertEquals(1, driver.Search.getPaths().size());
    assertEquals("/dir1", driver.Search.getPaths().get(0));
  }




}
