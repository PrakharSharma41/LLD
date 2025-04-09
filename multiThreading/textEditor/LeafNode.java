package textEditor;

    // Leaf node stores actual text
class LeafNode extends RopeNode {
        private StringBuilder text;
    
        public LeafNode(String text) {
            this.text = new StringBuilder(text);
        }
    
        @Override
        public int length() {
            return text.length();
        }
    
        @Override
        public String getText() {
            lock.readLock().lock();
            try {
                return text.toString();
            } finally {
                lock.readLock().unlock();
            }
        }
    
        @Override
        public void insert(int offset, String str) {
            lock.writeLock().lock();
            try {
                text.insert(offset, str);
            } finally {
                lock.writeLock().unlock();
            }
        }
    
        @Override
        public void delete(int offset, int len) {
            lock.writeLock().lock();
            try {
                text.delete(offset, offset + len);
            } finally {
                lock.writeLock().unlock();
            }
        }
    
        public boolean needsSplit(int maxSize) {
            return text.length() > maxSize;
        }
    
        public LeafNode splitRight(int splitIndex) {
            String rightText = text.substring(splitIndex);
            text.delete(splitIndex, text.length());
            return new LeafNode(rightText);
        }
    }