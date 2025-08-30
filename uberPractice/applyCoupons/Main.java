public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new CouponAll(5.0));
        cart.addItem(new CouponNext(10.0));
        cart.addItem(new CouponNth(10.0, 20, ProductType.PANT));
        cart.addItem(new Product(ProductType.PANT, 10.0));
        // cart.addItem(new CouponNTH(2, Enums.ProductType.WALLET, 2));
        cart.addItem(new Product(ProductType.SHIRT, 10.0));
        cart.addItem(new Product(ProductType.PANT, 10.0));
        double finalPrice = cart.checkout();
        System.out.println(finalPrice);
        
    }
}

