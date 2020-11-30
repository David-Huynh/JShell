package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.*;

public class FileTest {

	@Test
	public void testSetAndGetName() {
		File file = new File("fileN", "fileC", null);
		file.setName("abbas");
		assertEquals("abbas", file.getName());
	}

	@Test
	public void testSetAndGetParent() {
		File file = new File("fileN", "fileC", null);
		Directory newParent = new Directory("newParent", null);
		file.setParentDir(newParent);
		assertEquals(newParent, file.getParentDir());
	}

	@Test
	public void checkIsDirAndIsFile() {
		File file = new File("fileN", "fileC", null);
		assertFalse(file.isDirectory());
		assertTrue(file.isFile());
	}

	@Test
	public void testConstructor() {
		File file = new File("fileN", "fileC", null);
		assertEquals(file.getName(), "fileN");
		assertEquals(file.getContents(), "fileC");
		assertEquals(file.getParentDir(), null);
	}

	@Test
	public void testAppend() {
		File file = new File("fileN", "fileC", null);
		file.append(" appended text.");
		assertEquals(file.getContents(), "fileC appended text.");
	}

	@Test
	public void testOverwrite() {
		File file = new File("fileN", "fileC", null);
		file.overwrite("overwriten text.");
		assertEquals(file.getContents(), "overwriten text.");
	}

	@Test
	public void testClone() {
		File file = new File("fileN", "fileC", null);
		File clone = file.clone(null);
		assertEquals(file.getName(), clone.getName());
		assertEquals(file.getContents(), clone.getContents());
		assertNotEquals(file, clone);
	}
}
