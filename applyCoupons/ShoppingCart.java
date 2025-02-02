package applyCoupons;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product>productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }
    public void addToCart(Product product){
        Product p=new TypeCouponDecorator(new PercentageCouponDecorator(product,10),3,product.getType());
        productList.add(p);
    }
    public int getTotalPrice(){
        int totalPrice=0;
        for(Product product:productList){
            totalPrice+=product.getPrice();
        }
        return totalPrice;
    }
}
