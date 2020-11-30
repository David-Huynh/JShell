package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.Storage;

public class StorageTest {

	Storage storage;

	@Before
	public void setUp() throws Exception {
		storage = Storage.createNewStorage();
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
	 * Ensure the root is not null after initialization and that its name is /
	 */
	@Test
	public void testStorageInitialization() {
		assertFalse(storage.getRoot().equals(null));
		assertEquals("/", storage.getRoot().getName());
	}

	/**
	 * Test that if multiple storages are created, they are the same instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateNewStorage() throws Exception {
		Storage storage2 = Storage.createNewStorage();
		Storage storage3 = Storage.createNewStorage();
		Storage storage4 = Storage.createNewStorage();
		assertTrue(storage == storage2);
		assertTrue(storage2 == storage3);
		assertTrue(storage3 == storage4);
		assertTrue(storage4 == storage);
	}
}
