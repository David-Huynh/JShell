package driver;

import java.util.ArrayList;

public class Directory extends StorageUnit {

	private ArrayList<StorageUnit> contents = new ArrayList<StorageUnit>();

	public ArrayList<StorageUnit> getDirContents() {
		ArrayList<StorageUnit> temp = new ArrayList<StorageUnit>();
		temp = contents;
		return temp;
	}

}
