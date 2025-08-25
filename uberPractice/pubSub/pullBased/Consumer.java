package pullBased;

public interface Consumer {
    void subscribe(Topic topic);
    void pull(Topic topic);
    String getId();
}
