package driver;

import java.util.ArrayList;

public class Directory extends StorageUnit {

	private ArrayList<StorageUnit> contents = new ArrayList<StorageUnit>();

	public ArrayList<StorageUnit> getDirContents() {
		return contents;
	}

	public void addFile(StorageUnit fileName) {
		contents.add(fileName);
	}

	public void delFile(StorageUnit fileName) {
		contents.remove(fileName);
	}

	public Directory getDir() {
		return this;
	}

	public void setDirName(String name) {
		this.name = name;
	}

}
