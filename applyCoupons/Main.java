package applyCoupons;

public class Main {
    public static void main(String[] args) {
        Product item1=new Item1("fan", 1110, ProductType.ELECTRONIC);
        Product item2=new Item1("sofa", 1110, ProductType.FURNITURE);
        ShoppingCart cart=new ShoppingCart();
        cart.addToCart(item2);
        cart.addToCart(item1);
    }
}
