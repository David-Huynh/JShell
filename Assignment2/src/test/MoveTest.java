package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.JShell;
import driver.Move;

public class MoveTest {
	
	JShell shell;
	Directory root, dir1, dir2, dir21;
	File file2;
	File file1;
	@Before
	public void setUp() {
		shell = new JShell();
		root = shell.getRootDir();

		dir1 = new Directory("dir1", root);
		dir2 = new Directory("dir2", root);
		dir21 = new Directory("dir21", dir2);
		file1 = new File("file1", "file1contents", dir1);
		file2 = new File("file2", "file2contents", dir21);

		root.addFile(dir1);
		root.addFile(dir2);
		dir1.addFile(file1);
		dir2.addFile(dir21);
		dir21.addFile(file2);
	}

	@Test
	public void testExecuteMoveDir() {
		String[] parameters = {"mv", "dir1", "dir2"};
		
		Move.performOutcome(shell, parameters, 0, null);
		assertEquals(dir1.getName(), "dir1");
		assertEquals(dir1.getParentDir(), dir2);
	}
	
	@Test
	public void testExecuteMoveAndRenamingNewParentDir() {
		String[] parameters = {"mv", "dir1", "dir2/dir3"};
		Move.performOutcome(shell, parameters, 0, null);
		
		assertEquals(dir1.getName(), "dir3");
		assertEquals(dir1.getParentDir(), dir2);
	}
	
	@Test
	public void testExecuteMoveFileToDir() {
		String[] parameters = {"mv", "dir1/file1", "dir2"};
		Move.performOutcome(shell, parameters, 0, null);
		
		assertEquals(file1.getParentDir(), dir2);
	}
	
	@Test
	public void testExecuteMoveFileToFile() {
		String[] parameters = {"mv", "dir1/file1", "dir2/dir21/file2"};
		Move.performOutcome(shell, parameters, 0, null);
		
		assertEquals(file2.getContents(), "file1contents");
	}
	
	@Test //successful if error msg
	public void testDirDNE() {
		String[] parameters = {"mv", "dne", "dir1"};
		Move.performOutcome(shell, parameters, 0, null);
	}

	@After
	public void tearDown() {
		shell.exit();
		root = null;
		dir1 = null;
		dir2 = null;
		dir21 = null;
		file1 = null;
		file2 = null;
	}
}
