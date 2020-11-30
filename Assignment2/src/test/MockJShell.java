package test;

import driver.Directory;
import driver.JShellInterface;

public class MockJShell implements JShellInterface {
	
	Directory currDir;
	Directory rootDir;

	@Override
	public Directory getRootDir() {
		rootDir.setParentDir(rootDir);
		return rootDir;
	}

	@Override
	public Directory getCurrentDir() {
		return currDir;
	}

	public MockJShell() {
		rootDir = new Directory("/", null);
		rootDir.setParentDir(rootDir);
		currDir = rootDir;

	}
	
	@Override
	public void setCurrentDir(Directory dir) {
		this.currDir = dir;
	}

	@Override
	public void printError(String string) {
	}

}
