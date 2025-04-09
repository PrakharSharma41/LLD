package textEditor;

    // Internal node joins subtrees
    class InternalNode extends RopeNode {
        private RopeNode left, right;
        private int weight;
    
        public InternalNode(RopeNode left, RopeNode right) {
            this.left = left;
            this.right = right;
            this.weight = left.length();
        }
    
        @Override
        public int length() {
            return weight + (right != null ? right.length() : 0);
        }
    
        @Override
        public String getText() {
            lock.readLock().lock();
            try {
                return left.getText() + (right != null ? right.getText() : "");
            } finally {
                lock.readLock().unlock();
            }
        }
    
        @Override
        public void insert(int offset, String text) {
            lock.writeLock().lock();
            try {
                if (offset < weight) {
                    left.insert(offset, text);
                    weight = left.length();
                } else if (right != null) {
                    right.insert(offset - weight, text);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    
        @Override
        public void delete(int offset, int len) {
            lock.writeLock().lock();
            try {
                if (offset + len <= weight) {
                    left.delete(offset, len);
                    weight = left.length();
                } else if (offset >= weight && right != null) {
                    right.delete(offset - weight, len);
                } else {
                    int leftLen = weight - offset;
                    left.delete(offset, leftLen);
                    right.delete(0, len - leftLen);
                    weight = left.length();
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
    