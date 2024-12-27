package flyweightDesign;

public class DocumentCharacter implements ILetter{
    // keep intrinsic data which will remain constant
    private char character;
    private String fontType;
    private int size;
    public DocumentCharacter(char character, String fontType, int size) {
        this.character = character;
        this.fontType = fontType;
        this.size = size;
    }
    @Override
    public void display(int row, int column) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
    
}
