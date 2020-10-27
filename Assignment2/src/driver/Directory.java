package driver;

import java.util.ArrayList;

public class Directory extends StorageUnit {

	private ArrayList<StorageUnit> contents = new ArrayList<StorageUnit>();
	private Directory parentDir;

	public ArrayList<StorageUnit> getDirContents() {
		return contents;
	}

	public void addFile(StorageUnit fileName) {
		contents.add(fileName);
	}

	public void delFile(StorageUnit fileName) {
		contents.remove(fileName);
	}

	public String getDirName() {
		return this.name;
	}

	public void setDirName(String name) {
		this.name = name;
	}

	public Directory getParentDir() {
		return parentDir;
	}

	public void setParentDir(Directory parent) {
		this.parentDir = parent;
	}

	// function to check if a sub-directory named dirName is under Directory dir.
	// returns -1 if sub-directory named dirName is not in dir or index of sub-dir if it is
	public int isSubDir(String dirName) {
		int index = -1;
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).getClass().getSimpleName().equals("Directory")) {
				if (contents.get(i).name.equals(dirName)) {
					index = i;
				}
			}
		}

		return index;
	}
}
