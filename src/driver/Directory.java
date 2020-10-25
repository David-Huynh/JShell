package driver;

public class Directory extends StorageUnit {

	private StorageUnit[] contents = {};
	// hey collin i think it would be better if its an arraylist instead of an
	// array bc the length of an array is immutable
	public StorageUnit[] getDirContents() {
		StorageUnit[] temp = contents;

		return temp;
	}

}
