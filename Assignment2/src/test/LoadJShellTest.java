package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import driver.ListFiles;
import driver.LoadJShell;
import driver.MakeDirectory;
import driver.SaveJShell;
import org.junit.Before;
import org.junit.Test;
import driver.JShell;

public class LoadJShellTest {
	JShell shell;
	@Before
	public void setUp() {
		shell = new JShell();
	}
	@Test
	public void testGetManual() {
		String manual;
		manual = LoadJShell.getManual();
		assertEquals(manual, "loadJShell localFilePath\n"
				+ "Loads a previously saved JShell session onto a fresh "
				+ "JShell sessions.\n"
				+ "This command only works on fresh JShell sessions.");
	}
	@Test
	public void testPerformOutcome() {
		LoadJShell.performOutcome(shell,
				new String[]{"loadJShell", "./test.txt"}, 0, null);
		ListFiles.performOutcome(shell, new String[]{"ls"}, 0, null);
	}
}
