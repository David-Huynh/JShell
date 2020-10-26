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
}
