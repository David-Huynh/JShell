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
	 * Test to see if the right manual is returned for getManual
	 */
	@Test
	public void testGetManual() {
		String manual = new String();
		manual = Tree.getManual();
		assertEquals(manual, "tree\n"
				+ "The the tree command takes in no input "
				+ "parameter.\nWhen the user types in the tree you must, "
				+ "starting from the root directory (‘\\’) display the "
				+ "entire file\n"
				+ "system as a tree. For every level of the tree, you must "
				+ "indent by a tab character.");
	}

	/**
	 * Test that entering more parameters won't work
	 */
	@Test
	public void testPerformOutcomeMoreThanOneParam() {
		String[] parameters = {"tree", "random", "stuff"};
		Tree.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(), "");
	}

	/**
	 * Test printing the tree for an empty root.
	 */
	@Test
	public void testPerformOutcomeWithEmptyRoot() {
		String[] parameters = {"tree"};
		Tree.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(), "/\n");
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
				"/\n\tfile1\n\tfile2\n\tdir1\n\tdir2\n");
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
		String[] parameters = {"tree"};
		Tree.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(),
				"/\n\tfile1\n\tfile2\n\tdir1\n\tdir2\n");
	}
}
