package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.StorageUnit;

public class DirectoryTest {

	Directory dir;
	Directory parentOfDir;
	Directory grandparentOfDir;

	@Before
	public void setUp() throws Exception {
		// This guy's parent will never be reached so just set it to null
		grandparentOfDir = new Directory("grandparentOfDir", null);
		parentOfDir = new Directory("parentOfDir", grandparentOfDir);
		dir = new Directory("dir", parentOfDir);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetAndGetName() {
		dir.setName("abbas");
		assertEquals("abbas", dir.getName());
	}

	@Test
	public void testSetAndGetParent() {
		Directory newParent = new Directory("newParent", null);
		dir.setParentDir(newParent);
		assertEquals(newParent, dir.getParentDir());
	}

	@Test
	public void checkIsDirAndIsFile() {
		assertTrue(dir.isDirectory());
		assertFalse(dir.isFile());
	}

	@Test
	public void testDirectory() {
		assertEquals("dir", dir.getName());
		assertEquals(parentOfDir, dir.getParentDir());
	}

	@Test
	public void testEqualsObject() {
		Directory newDir = new Directory("dir", parentOfDir);
		assertTrue(dir.equals(newDir));
	}

	@Test
	public void testGetDirContentsEmpty() {
		assertTrue(dir.getDirContents().isEmpty());
	}

	@Test
	public void testGetDirContentsAndAddFile() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		ArrayList<StorageUnit> expected = new ArrayList<StorageUnit>();
		expected.add(content1);
		expected.add(content2);
		expected.add(content3);
		expected.add(content4);
		expected.add(content5);
		expected.add(content6);
		assertEquals(expected, dir.getDirContents());
	}

	/**
	 * Deleting a file at the beginning
	 */
	@Test
	public void testDelFileBeginning() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		ArrayList<StorageUnit> expected = new ArrayList<StorageUnit>();
		expected.add(content2);
		expected.add(content3);
		expected.add(content4);
		expected.add(content5);
		expected.add(content6);
		dir.delFile(content1);
		assertEquals(expected, dir.getDirContents());
	}

	/**
	 * Deleting a file in the middle
	 */
	@Test
	public void testDelFileMiddle() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		ArrayList<StorageUnit> expected = new ArrayList<StorageUnit>();
		expected.add(content1);
		expected.add(content2);
		expected.add(content4);
		expected.add(content5);
		expected.add(content6);
		dir.delFile(content3);
		assertEquals(expected, dir.getDirContents());
	}

	@Test
	public void testGetFile() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		assertEquals(content1, dir.getFile(0));
		assertEquals(content2, dir.getFile(1));
		assertEquals(content3, dir.getFile(2));
		assertEquals(content4, dir.getFile(3));
		assertEquals(content5, dir.getFile(4));
		assertEquals(content6, dir.getFile(5));
	}

	@Test
	public void testIsSubDirDoesntExist() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		assertEquals(-1, dir.isSubDir("content7"));
	}

	@Test
	public void testIsSubDir() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		assertEquals(1, dir.isSubDir("content2"));
		assertEquals(3, dir.isSubDir("content4"));
		assertEquals(-1, dir.isSubDir("content1"));
	}

	@Test
	public void testContainsFile() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		assertEquals(0, dir.containsFile("content1"));
		assertEquals(-1, dir.containsFile("content2"));
		assertEquals(2, dir.containsFile("content3"));
		assertEquals(-1, dir.containsFile("content4"));
		assertEquals(4, dir.containsFile("content5"));
		assertEquals(-1, dir.containsFile("content6"));
		assertEquals(-1, dir.containsFile("content7"));
	}

	@Test
	public void testIterator() {
		File content1 = new File("content1", "hello", dir);
		Directory content2 = new Directory("content2", dir);
		File content3 = new File("content3", "bye", dir);
		Directory content4 = new Directory("content4", dir);
		File content5 = new File("content5", "see you", dir);
		Directory content6 = new Directory("content6", dir);
		dir.addFile(content1);
		dir.addFile(content2);
		dir.addFile(content3);
		dir.addFile(content4);
		dir.addFile(content5);
		dir.addFile(content6);
		ArrayList<StorageUnit> expected = new ArrayList<StorageUnit>();
		for (StorageUnit unit : dir) {
			expected.add(unit);
		}
		assertEquals(expected, dir.getDirContents());
	}

}
