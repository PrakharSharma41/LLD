import java.util.ArrayList;
import java.util.List;

public class ShoppingCart{
    List<CartItem>cartItems;
    double finalPrice;
    ShoppingCart(){
        cartItems=new ArrayList<>();
        finalPrice=0;
    }
    void addItem(CartItem item){
        cartItems.add(item);
    }
    double checkout(){
        applyAllCoupon();
        for(CartItem item:cartItems){
            if(item.isCoupon())continue;
            Product product=(Product)item;
            finalPrice+=product.applyCoupons();
        }
        return finalPrice;
    }
    void applyAllCoupon(){
        for(CartItem item:cartItems){
            if(item.isCoupon()==false){                
                continue;
            }
            Coupon coupon = (Coupon) item;
            coupon.applyCoupon(cartItems, coupon);
        }
    }
}
