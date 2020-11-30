package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.JShell;
import driver.Storage;
import driver.Tree;

public class TreeTest {

	JShell shell;
	File stdOutFile;

	/**
	 * Set up a new JShell and a File to send StdOut to.
	 */
	@Before
	public void setUp() {
		shell = new JShell();
		stdOutFile = new File("file", "", null);
	}

	/**
	 * Destroy the only reference to the storage system
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		Field field = Storage.class.getDeclaredField("onlyReference");
		field.setAccessible(true);
		field.set(null, null);
	}

	/**
	 * Test to see if the right manual is returned for getManual
	 */
	@Test
	public void testGetManual() {
		String manual = new String();
		manual = Tree.getManual();
		assertEquals(manual, "tree\n"
				+ "The the tree command takes in no input "
				+ "parameter.\nWhen the user types in the tree you must, "
				+ "starting from the root directory (‘\\’)\ndisplay the "
				+ "entire file"
				+ "system as a tree. For every level of the tree, you must "
				+ "\nindent by a tab character.");
	}

	/**
	 * Test that entering more parameters won't work
	 */
	@Test
	public void testPerformOutcomeMoreThanOneParam() {
		String[] parameters = {"tree", "random", "stuff"};
		Tree.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(), "");
		// i.e. assert that stdOutFile is empty
	}

	/**
	 * Test printing the tree for an empty root.
	 */
	@Test
	public void testPerformOutcomeWithEmptyRoot() {
		String[] parameters = {"tree"};
		Tree.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(), "/");
	}

	/**
	 * Test printing the tree, the root has multiple directories and files.
	 */
	@Test
	public void testPerformOutcomeWithSomeFilesInRoot() {
		File file1 = new File("file1", "stuff", shell.getRootDir());
		File file2 = new File("file2", "stuff", shell.getRootDir());
		Directory dir1 = new Directory("dir1", shell.getRootDir());
		Directory dir2 = new Directory("dir2", shell.getRootDir());
		shell.getRootDir().addFile(file1);
		shell.getRootDir().addFile(file2);
		shell.getRootDir().addFile(dir1);
		shell.getRootDir().addFile(dir2);
		String[] parameters = {"tree"};
		Tree.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(),
				"/\n\tfile1\n\tfile2\n\tdir1\n\tdir2");
	}

	/**
	 * Test printing the tree, the root has multiple directories and files, some
	 * directories have even more subdirectories and files in them.
	 */
	@Test
	public void testPerformOutcomeWithDirectoriesWithDepth() {
		File file1 = new File("file1", "stuff", shell.getRootDir());
		File file2 = new File("file2", "stuff", shell.getRootDir());
		Directory dir1 = new Directory("dir1", shell.getRootDir());
		Directory dir2 = new Directory("dir2", shell.getRootDir());
		shell.getRootDir().addFile(file1);
		shell.getRootDir().addFile(file2);
		shell.getRootDir().addFile(dir1);
		shell.getRootDir().addFile(dir2);
		// (above is same as before, here come new subfiles)
		File file3 = new File("file3", "stuff", dir1);
		dir1.addFile(file3);
		Directory dir3 = new Directory("dir3", dir1);
		dir1.addFile(dir3);
		Directory dir4 = new Directory("dir4", dir3);
		dir3.addFile(dir4);
		File file4 = new File("file4", "stuff", dir2);
		dir2.addFile(file4);
		String[] parameters = {"tree"};
		Tree.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(),
				"/\n\tfile1\n\tfile2\n\tdir1\n\t\tfile3\n\t\tdir3\n\t\t\tdir4"
						+ "\n\tdir2\n\t\tfile4");
	}
}
