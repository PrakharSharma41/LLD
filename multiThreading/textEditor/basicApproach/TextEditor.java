package textEditor.basicApproach;

public interface TextEditor {
    void insert(int offset, String text);
    void delete (int offset, int length);
    String getContents();    
}
