// **********************************************************
// Assignment2:
// Student1: Collin Chan
// UTORID user_name: chancol7
// UT Student #: 1006200889
// Author: Collin Chan
//
// Student2: Jeff He
// UTORID user_name: Hejeff2
// UT Student #: 1006398783
// Author: Jeff He
//
// Student3: Nevin Wong
// UTORID user_name: wongnevi
// UT Student #: 1005391434
// Author: Nevin Wong
//
// Student4: David Huynh
// UTORID user_name: huynhd12
// UT Student #: 1005991937
// Author: David Huynh
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package driver;

import java.util.ArrayList;

public class ListFiles extends ShellCommand {

	public static String getManual() {
		return "ls [PATH ...] \nIf no paths are given, print the contents "
				+ "(file or directory) of the current \ndirectory, with a new "
				+ "line following each of the content (file or directory). \n"
				+ "Otherwise, for each path p, the order listed: \n    · If p "
				+ "specifies a file, print p \n    · If p specifies a "
				+ "directory, print p, a colon, then the contents of that \n"
				+ "      directory, then an extra new line. \n    · If p does "
				+ "not exist, print a suitable message.  ";
	}

	public static void performOutcome(JShell shell, String[] parameters) {
		if (parameters.length > 2) {
			PrintError.reportError(shell, "man",
					"Invalid number of arguments.");
			return;
		}
		
		Directory currDir = shell.getCurrentDir();
		ArrayList<StorageUnit> fileList = currDir.getDirContents();
		int directoryIndex = -1;
		int fileIndex = -1;

		if (parameters.length == 1) // Case 1: Called with no parameters, list
									// files of current directory
		{
			list(shell, fileList);
		} else {
			for (int i = 1; i < parameters.length; i++) // Case 2: Called with
														// >= 1 parameters,
														// index through each of
														// them
			{
				if (parameters[i].contains("\\")) // check if parameter is a
													// path with > 1 directories
				{

				} else // parameter is a file or directory in current directory
				{
					directoryIndex = currDir.isSubDir(parameters[i]);
					fileIndex = currDir.containsFile(parameters[i]);

					if (directoryIndex != -1) // check if parameter is a
												// directory in current
												// directory
					{
						shell.println(parameters[i] + ":");
						list(shell, ((Directory) fileList.get(directoryIndex))
								.getDirContents());
					} else if (fileIndex != -1) // check if parameter is a file
												// in current directory
					{
						shell.println(parameters[i]);
					} else // parameter is not in current directory
					{
						PrintError.reportError(shell, "ls",
								"Cannot access '" + parameters[i]
										+ "', no such file or directory.");
					}
				}
			}
		}
	}

	public static void list(JShell shell, ArrayList<StorageUnit> fileList)
	// Function used to print all files in directory
	{
		for (int i = 0; i < fileList.size(); i++) {
			System.out.println(fileList.get(i).name);
		}
	}

}
