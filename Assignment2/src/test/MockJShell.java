package test;

import java.util.Stack;

import driver.Directory;
import driver.JShellInterface;

public class MockJShell implements JShellInterface {
	
	Directory currDir;
	Directory rootDir;
	Stack<Directory> dirStack;

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
		dirStack = new Stack<Directory>();

	}
	
	@Override
	public void setCurrentDir(Directory dir) {
		this.currDir = dir;
	}

	@Override
	public void printError(String string) {
	}

	@Override
	public Stack<Directory> getDirStack() {
		return dirStack;
	}
}
