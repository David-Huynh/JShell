package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.File;
import driver.JShell;
import driver.Manual;

public class ManualTest {

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
		manual = Manual.getManual();
		assertEquals(manual, "man CMD\n" + "Print documentation for CMD(s)");
	}

	/**
	 * Test getting the manual for man.
	 */
	@Test
	public void testPerformOutcomeForMan() {
		String[] parameters = {"man", "man"};
		Manual.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(),
				"man CMD\n" + "Print documentation for CMD(s)\n");
	}

	/**
	 * Test getting the manual for cp.
	 */
	@Test
	public void testPerformOutcomeForCp() {
		String[] parameters = {"man", "cp"};
		Manual.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(),
				"cp OLDPATH NEWPATH\n"
						+ "Like mv, but don’t remove OLDPATH. If OLDPATH\n"
						+ "is a directory, recursively copy the contents.\n");
	}

	/**
	 * Test that getting the manual that doesn't exist will not work.
	 */
	@Test
	public void testPerformOutcomeForNonExistentMethod() {
		String[] parameters = {"man", "abbas"};
		Manual.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(), "");
	}
	
	/**
	 * Test that getting the manuals for multiple commands will not work.
	 */
	@Test
	public void testPerformOutcomeForMultipleCommands() {
		String[] parameters = {"man", "cp", "history"};
		Manual.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(), "");
	}
	
	/**
	 * Test that just entering man without any other arguments will not work.
	 */
	@Test
	public void testPerformOutcomeForNoCommands() {
		String[] parameters = {"man"};
		Manual.performOutcome(shell, parameters, 1, stdOutFile);
		assertEquals(stdOutFile.getContents(), "");
	}
}
