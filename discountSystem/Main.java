import java.util.List;

public class Main {
    public static void main(String[] args) {
        DiscountManager mgr = DiscountManager.getInstance();
        mgr.registerDiscount(new SeasonalOffer(10, "Clothing"));
        // mgr.registerCoupon(new LoyaltyDiscount(5));
        // mgr.registerCoupon(new BulkPurchaseDiscount(1000, 100));
        mgr.registerDiscount(new BankingCoupon("ABC", 2000, 15, 500));

        Product p1 = new Product("Winter Jacket", "Clothing", 1000);
        Product p2 = new Product("Smartphone", "Electronics", 20000);
        Product p3 = new Product("Jeans", "Clothing", 1000);
        Product p4 = new Product("Headphones", "Electronics", 2000);

        Cart cart = new Cart();
        cart.addProduct(p1, 1);
        cart.addProduct(p2, 1);
        cart.addProduct(p3, 2);
        cart.addProduct(p4, 1);
        cart.setLoyaltyMember(true);
        cart.setPaymentBank("ABC");

        System.out.println("Original Cart Total: " + cart.getOriginalTotal() + " Rs");

        List<String> applicable = mgr.getApplicable(cart);
        System.out.println("Applicable Coupons:");
        for (String name : applicable) {
            System.out.println(" - " + name);
        }

        double finalTotal = mgr.applyAll(cart);
        System.out.println("Final Cart Total after discounts: " + finalTotal + " Rs");
    }
}
