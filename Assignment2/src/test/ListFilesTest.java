package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.JShell;
import driver.StdOut;
import driver.ListFiles;

public class ListFilesTest {
	
	JShell shell;
	Directory root;
	
	@Before
	public void setUp() {
		shell = new JShell();
		root = shell.getRootDir();
	}
	
	@Test
	public void testListEmpty() {
		File file = new File("name", "asdf", null);
		StdOut stdout = new StdOut(shell, 1, file);
		ListFiles.list(stdout, root, "");
		assertEquals("", file.getContents());
	}
	
	@Test
	public void testListMultipleDir() {
		File file = new File("name", "asdf", null);
		StdOut stdout = new StdOut(shell, 1, file);
		Directory dir1 = new Directory("dir1", root);
		Directory dir2 = new Directory("dir2", root);
		File file1 = new File("file1", "file1contents", dir1);
		root.addFile(dir1);
		root.addFile(dir2);
		dir1.addFile(file1);
		ListFiles.list(stdout, root, "");
		System.out.println(file.getContents());
		assertEquals("", file.getContents());
	}

}
