package textEditor;

public class RopeTextEditor {
    private static final int MAX_LEAF_SIZE = 64;
    private RopeNode root;

    public RopeTextEditor(String initialText) {
        this.root = new LeafNode(initialText);
        balanceIfNeeded();
    }

    public void insert(int offset, String text) {
        root.insert(offset, text);
        balanceIfNeeded();
    }

    public void delete(int offset, int length) {
        root.delete(offset, length);
        balanceIfNeeded();
    }

    public String getContents() {
        return root.getText();
    }

    private void balanceIfNeeded() {
        if (root instanceof LeafNode) {
            LeafNode leaf = (LeafNode) root;
            if (leaf.needsSplit(MAX_LEAF_SIZE)) {
                int mid = leaf.length() / 2;
                LeafNode right = leaf.splitRight(mid);
                root = new InternalNode(leaf, right);
            }
        }
    }
}
