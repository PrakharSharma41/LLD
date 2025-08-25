public class Order {
    int id;
    String symbol;
    double price;
    long timestamp;
    public String getSymbol() {
        return symbol;
    }
    int quantity;
    @Override
    public String toString() {
        return "Order [id=" + id + ", symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + ", orderType="
                + orderType + "]";
    }
    OrderType orderType;
    public Order(int id, String symbol, double price, long timestamp, int quantity,OrderType orderType) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.orderType=orderType;
    }
    public double getPrice() {
        return price;
    }
    public OrderType getOrderType() {
        return orderType;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public int getQuantity() {
        return quantity;
    }
    public void reduceQuantity(int quantity) {
        this.quantity-=quantity;
    }
}
enum OrderType{
    BUY,SELL;
}
