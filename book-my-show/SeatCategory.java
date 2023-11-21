
enum SeatCategory {
    ROYAL(400),
    NORMAL(200), 
    RECLINER(100);    
    private int defaultValue;

    SeatCategory(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getDefaultValue() {
        return defaultValue;
    }
}
