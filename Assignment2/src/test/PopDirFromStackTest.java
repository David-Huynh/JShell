package test;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.JShell;
import driver.PopDirFromStack;

public class PopDirFromStackTest {

	JShell shell;
	Directory root, dir1, dir2, dir21;
	File file1, file2;
	Stack<Directory> dirStack;;

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

		dirStack = shell.getDirStack();
	}

	@Test
	public void testParametersNumFail() {
		String[] test0 = {"popd", "sadfa"};
		String[] test1 = {"popd", "dir1"};
		String[] test2 = {"popd", "/dir1"};
		String[] test3 = {"popd", "file1", "sadfa", "saddfv"};

		assertEquals(PopDirFromStack.checkParam(test0.length, shell), false);
		assertEquals(PopDirFromStack.checkParam(test1.length, shell), false);
		assertEquals(PopDirFromStack.checkParam(test2.length, shell), false);
		assertEquals(PopDirFromStack.checkParam(test3.length, shell), false);
	}

	@Test
	public void testEmptyStack() {
		assertEquals(PopDirFromStack.checkParam(dirStack.size(), shell), false);
	}

	@Test
	public void testOneElementStack() {
		dirStack.add(dir1);
		assertEquals(PopDirFromStack.checkStackSize(dirStack.size(), shell),
				true);
	}

	@Test
	public void testMultipleElementStack() {
		dirStack.add(dir1);
		dirStack.add(dir2);
		dirStack.add(dir21);
		assertEquals(PopDirFromStack.checkStackSize(dirStack.size(), shell),
				true);
	}

	@Test
	public void testOutcomeOneElementStack() {
		dirStack.add(dir1);
		String[] param = {"popd"};
		assertEquals(dirStack.size(), 1);

		PopDirFromStack.performOutcome(shell, param, 0, null);

		assertEquals(dirStack.size(), 0);
		assertEquals(shell.getCurrentDir(), dir1);
	}

	@Test
	public void testOutcomeMultipleElementStack() {
		dirStack.add(dir1);
		dirStack.add(dir2);
		dirStack.add(dir21);
		String[] param = {"popd"};
		assertEquals(dirStack.size(), 3);
		assertEquals(dirStack.peek(), dir21);

		PopDirFromStack.performOutcome(shell, param, 0, null);

		assertEquals(dirStack.size(), 2);
		assertEquals(shell.getCurrentDir(), dir21);
		assertEquals(dirStack.peek(), dir2);
	}

}
