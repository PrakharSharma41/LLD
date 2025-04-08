public class File implements FileSystemAttributes{
    int fileSize;
    String name;
    File(int fileSize,String name){
        this.fileSize=fileSize;this.name=name;
    }
    public int getFileSize() {
        return fileSize;
    }
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
