package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.File;
import driver.JShell;
import driver.Storage;

public class ShellCommandTest {

	/** Used to test print statements */
	private final PrintStream printed = System.out;
	private final ByteArrayOutputStream consoleStreamCaptor = 
			new ByteArrayOutputStream();

	@Before
	public void setUp() {
		System.setOut(new PrintStream(consoleStreamCaptor));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(printed);
	}

	@Test
	public void testProducesStdOutForStdOutProducingCmd() {
		assertTrue(MockStdOutCommand.producesStdOut());
	}

	@Test
	public void testProducesStdOutForNonStdOutProducingCmd() {
		assertFalse(MockNonStdOutCommand.producesStdOut());
	}

	@Test
	public void testGetManualForCmd1() {
		assertEquals("cmd1 \nI am a command that produces StdOut.",
				MockStdOutCommand.getManual());
	}

	@Test
	public void testGetManualForCmd2() {
		assertEquals("cmd2 \nI am a command that does not produce StdOut.",
				MockNonStdOutCommand.getManual());
	}

	/**
	 * Test performOutcome We will only use cmd1 because it does not differ from
	 * cmd2 in this method.
	 */
	@Test
	public void testPerformOutcome() {
		JShell expectedShell = new JShell();
		String expectedParams[] = {"param1", "param2", "param3", "param4"};
		int expectedOutputType = 0;
		File expectedFile = new File("expectedFile", "blabla", null);
		MockStdOutCommand.performOutcome(expectedShell, expectedParams,
				expectedOutputType, expectedFile);
		// Ensure that the command got the right information
		assertEquals(MockStdOutCommand.receivedShell, expectedShell);
		assertArrayEquals(MockStdOutCommand.receivedParams, expectedParams);
		assertEquals(MockStdOutCommand.receivedOutputFile, expectedFile);
		assertEquals(MockStdOutCommand.receivedOutputType, expectedOutputType);
		// Ensure the command is done by seeing if the prompt is printed
		assertEquals("I am cmd1 and I have just been called.",
				consoleStreamCaptor.toString().trim());
	}

}
