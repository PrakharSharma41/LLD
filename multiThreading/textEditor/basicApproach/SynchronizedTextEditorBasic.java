package textEditor.basicApproach;

public class SynchronizedTextEditorBasic implements TextEditor{
    private final StringBuffer buffer;

    public SynchronizedTextEditorBasic() {
        this.buffer = new StringBuffer();
    }

    @Override
    public void insert(int offset, String text) {
        if (offset < 0 || offset > buffer.length()) {
            throw new IndexOutOfBoundsException("Invalid offset for insert.");
        }
        buffer.insert(offset, text);
    }

    @Override
    public void delete(int offset, int length) {
        if (offset < 0 || offset + length > buffer.length()) {
            throw new IndexOutOfBoundsException("Invalid offset/length for delete.");
        }
        buffer.delete(offset, offset + length);
    }

    @Override
    public String getContents() {
        return buffer.toString();
    }
    
}
