package pullBased;

public class Message {
    String id;
    String content;
    Message(String id,String content){
        this.id=id;
        this.content=content;
    }
    @Override
    public String toString() {
        return "Message [id=" + id + ", content=" + content + "]";
    }
}
