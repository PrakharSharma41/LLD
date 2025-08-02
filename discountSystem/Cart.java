import java.util.ArrayList;
import java.util.List;

class Cart {
    private List<CartItem> items = new ArrayList<>();
    private double originalTotal = 0.0;
    private double currentTotal  = 0.0; // total after applying discounts
    private boolean loyaltyMember;
    private String paymentBank;

    public Cart() {
        this.loyaltyMember = false;
        this.paymentBank   = "";
    }

    public void addProduct(Product prod, int qty) {
        CartItem item = new CartItem(prod, qty);
        items.add(item);
        originalTotal += item.itemTotal();
        currentTotal  += item.itemTotal();
    }

    public double getOriginalTotal() {
        return originalTotal;
    }

    public double getCurrentTotal() {
        return currentTotal;
    }

    public void applyDiscount(double d) {
        currentTotal -= d;
        if (currentTotal < 0) {
            currentTotal = 0;
        }
    }

    public void setLoyaltyMember(boolean member) {
        this.loyaltyMember = member;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public void setPaymentBank(String bank) {
        this.paymentBank = bank;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public List<CartItem> getItems() {
        return items;
    }
}