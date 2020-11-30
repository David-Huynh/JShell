package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.ChangeDirectory;
import driver.Directory;
import driver.File;
import driver.JShell;
import driver.PushDirOntoStack;
import driver.Storage;

public class ChangeDirectoryTest {

	JShell shell;

	/** Used to test print statements */
	private final PrintStream printed = System.out;
	private final ByteArrayOutputStream consoleStreamCaptor = 
			new ByteArrayOutputStream();

	/**
	 * Set up a new JShell and a File to send StdOut to.
	 */
	@Before
	public void setUp() {
		shell = new JShell();
		System.setOut(new PrintStream(consoleStreamCaptor));

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
		System.setOut(printed);
	}

	@Test
	public void testProducesStdOut() {
		assertFalse(PushDirOntoStack.producesStdOut());
	}

	@Test
	public void testGetManual() {
		assertEquals("cd DIR \nChange directory to DIR, which may be relative "
				+ "to the " + "current directory or \nmay be a full path. As "
				+ "with Unix, .. "
				+ "means a parent directory and a . means \nthe " + "current "
				+ "directory. The directory must be /, the forwa"
				+ "rd slash. The " + "foot of \nthe file system is a single sla"
				+ "sh: /.  ", ChangeDirectory.getManual());
	}

	@Test
	public void testPerformOutcomeOneParam() {
		String[] parameters = {"cd"};
		ChangeDirectory.performOutcome(shell, parameters, 0, null);
		assertEquals("cd: Invalid number of arguments.",
				consoleStreamCaptor.toString().trim());
	}

	@Test
	public void testPerformOutcomeThreeParams() {
		String[] parameters = {"cd", "/dir", "two"};
		ChangeDirectory.performOutcome(shell, parameters, 0, null);
		assertEquals("cd: Invalid number of arguments.",
				consoleStreamCaptor.toString().trim());
	}

	@Test
	public void testPerformOutcomeThreeParams2() {
		String[] parameters = {"cd", "dir2", "dir3"};
		ChangeDirectory.performOutcome(shell, parameters, 0, null);
		assertEquals("cd: Invalid number of arguments.",
				consoleStreamCaptor.toString().trim());
	}

	@Test
	public void testPerformOutcomeGoalInCurrentDir() {
		File file1 = new File("file1", "stuff", shell.getRootDir());
		File file2 = new File("file2", "stuff", shell.getRootDir());
		Directory dir1 = new Directory("dir1", shell.getRootDir());
		Directory dir2 = new Directory("dir2", shell.getRootDir());
		shell.getRootDir().addFile(file1);
		shell.getRootDir().addFile(file2);
		shell.getRootDir().addFile(dir1);
		shell.getRootDir().addFile(dir2);
		File file3 = new File("file3", "stuff", dir1);
		dir1.addFile(file3);
		Directory dir3 = new Directory("dir3", dir1);
		dir1.addFile(dir3);
		Directory dir4 = new Directory("dir4", dir3);
		dir3.addFile(dir4);
		File file4 = new File("file4", "stuff", dir2);
		dir2.addFile(file4);
		//    At this point: the storage system looks like this:
		//    Say we want to cd into dir2
		//
		//    / <-- CURRENT DIRECTORY
		//        file1
		//        file2
		//        dir1
		//	          file3
		//	          dir3
		//		          dir4
		//        dir2 <-- GOAL
		//	          file4
		String[] parameters = {"cd", "dir2"};
		ChangeDirectory.performOutcome(shell, parameters, 0, null);
		// Test that the stack picked up the root and the shell changed its
		// current directory to dir2
		assertEquals(dir2, shell.getCurrentDir());
	}
	
	@Test
	public void testPerformOutcomePushNonRootDirOntoStack() {
		File file1 = new File("file1", "stuff", shell.getRootDir());
		File file2 = new File("file2", "stuff", shell.getRootDir());
		Directory dir1 = new Directory("dir1", shell.getRootDir());
		Directory dir2 = new Directory("dir2", shell.getRootDir());
		shell.getRootDir().addFile(file1);
		shell.getRootDir().addFile(file2);
		shell.getRootDir().addFile(dir1);
		shell.getRootDir().addFile(dir2);
		File file3 = new File("file3", "stuff", dir1);
		dir1.addFile(file3);
		Directory dir3 = new Directory("dir3", dir1);
		dir1.addFile(dir3);
		Directory dir4 = new Directory("dir4", dir3);
		dir3.addFile(dir4);
		File file4 = new File("file4", "stuff", dir2);
		dir2.addFile(file4);
		shell.setCurrentDir(dir2);
		//    At this point: the storage system looks like this:
		//    Say we want to cd into dir1
		//
		//    /
		//        file1
		//        file2
		//        dir1 <-- GOAL
		//	          file3
		//	          dir3
		//		          dir4
		//        dir2 <-- CURRENT DIRECTORY
		//	          file4
		String[] parameters = {"cd", "/dir1"};
		ChangeDirectory.performOutcome(shell, parameters, 0, null);
		// Test that the stack picked up the root and the shell changed its
		// current directory to dir2
		assertEquals(dir1, shell.getCurrentDir());
	}

	@Test
	public void testPerformOutcomeWithAbsPath() {
		File file1 = new File("file1", "stuff", shell.getRootDir());
		File file2 = new File("file2", "stuff", shell.getRootDir());
		Directory dir1 = new Directory("dir1", shell.getRootDir());
		Directory dir2 = new Directory("dir2", shell.getRootDir());
		shell.getRootDir().addFile(file1);
		shell.getRootDir().addFile(file2);
		shell.getRootDir().addFile(dir1);
		shell.getRootDir().addFile(dir2);
		File file3 = new File("file3", "stuff", dir1);
		dir1.addFile(file3);
		Directory dir3 = new Directory("dir3", dir1);
		dir1.addFile(dir3);
		Directory dir4 = new Directory("dir4", dir3);
		dir3.addFile(dir4);
		File file4 = new File("file4", "stuff", dir2);
		dir2.addFile(file4);
		shell.setCurrentDir(dir2);
		//    At this point: the storage system looks like this:
		//    Say we want to cd into dir1
		//
		//    /
		//        file1
		//        file2
		//        dir1
		//	          file3
		//	          dir3
		//		          dir4 <-- GOAL
		//        dir2 <-- CURRENT DIRECTORY
		//	          file4
		String[] parameters = {"cd", "../dir1/dir3/dir4"};
		ChangeDirectory.performOutcome(shell, parameters, 0, null);
		// Test that the stack picked up the root and the shell changed its
		// current directory to dir2
		assertEquals(dir4, shell.getCurrentDir());
	}
	
	@Test
	public void testPerformOutcomeWithRelPath() {
		File file1 = new File("file1", "stuff", shell.getRootDir());
		File file2 = new File("file2", "stuff", shell.getRootDir());
		Directory dir1 = new Directory("dir1", shell.getRootDir());
		Directory dir2 = new Directory("dir2", shell.getRootDir());
		shell.getRootDir().addFile(file1);
		shell.getRootDir().addFile(file2);
		shell.getRootDir().addFile(dir1);
		shell.getRootDir().addFile(dir2);
		File file3 = new File("file3", "stuff", dir1);
		dir1.addFile(file3);
		Directory dir3 = new Directory("dir3", dir1);
		dir1.addFile(dir3);
		Directory dir4 = new Directory("dir4", dir3);
		dir3.addFile(dir4);
		File file4 = new File("file4", "stuff", dir2);
		dir2.addFile(file4);
		shell.setCurrentDir(dir2);
		//    At this point: the storage system looks like this:
		//    Say we want to cd into dir1
		//
		//    /
		//        file1
		//        file2
		//        dir1
		//	          file3
		//	          dir3
		//		          dir4 <-- GOAL
		//        dir2 <-- CURRENT DIRECTORY
		//	          file4
		String[] parameters = {"cd", "/dir1/dir3/dir4"};
		ChangeDirectory.performOutcome(shell, parameters, 0, null);
		// Test that the stack picked up the root and the shell changed its
		// current directory to dir2
		assertEquals(dir4, shell.getCurrentDir());
	}
}
