package driver;

public class File extends StorageUnit {
  
  private String contents;
  
  public File(String text)
  {
    contents = text;
  }
  
  public void append(String text)
  {
    this.contents = this.contents + text;
  }
  
  public void print()
  {
    System.out.println(this.contents + "\n\n\n");
  }
}
