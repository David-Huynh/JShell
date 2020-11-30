package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.*;

public class MakeDirectoryTest {
	JShell shell;
	Directory root;

	@Before
	public void setup() {
		shell = new JShell();
		root = shell.getRootDir();
	}

	@Test
	public void testPerformOutComeWithOneRelative() {
		String[] parameters = {"mkdir", "dir1"};
		driver.MakeDirectory.performOutcome(shell, parameters, 0, null);
		assertEquals("dir1", root.getDirContents().get(0).getName());
		assertEquals(root, root.getDirContents().get(0).getParentDir());
	}

	@Test
	public void testPerformOutComeWithAbsAndRelative() {
		String[] parameters = {"mkdir", "dir2", "/dir3", "dir4"};
		driver.MakeDirectory.performOutcome(shell, parameters, 0, null);
		assertEquals("dir2", root.getDirContents().get(1).getName());
		assertEquals(root, root.getDirContents().get(1).getParentDir());
		assertEquals("dir3", root.getDirContents().get(2).getName());
		assertEquals(root, root.getDirContents().get(2).getParentDir());
		assertEquals("dir4", root.getDirContents().get(3).getName());
		assertEquals(root, root.getDirContents().get(3).getParentDir());
	}
}
