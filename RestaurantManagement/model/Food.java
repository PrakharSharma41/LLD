package RestaurantManagement.model;

public class Food {
    String name;
    int quantity;
    double price;
    public Food(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public void updateQuantity(Integer quantityToAdd) {
        quantity+=quantityToAdd;
    }
}
