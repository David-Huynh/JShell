package driver;

public interface JShellInterface {

	Directory getRootDir();

	void setCurrentDir(Directory dir);

	Directory getCurrentDir();

	void printError(String string);

}
