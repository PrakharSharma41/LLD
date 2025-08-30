import java.util.ArrayList;
import java.util.List;

public class Product implements CartItem{

    ProductType productType;
    List<Coupon>coupons;
    double price;
    
    public Product(ProductType productType, double price) {
        this.productType = productType;
        this.price = price;
        coupons=new ArrayList<>();
    }
    @Override
    public boolean isCoupon() {
        return false;
    }
    void addCoupon(Coupon coupon){
        coupons.add(coupon);
    }
    double applyCoupons(){
        double finalPrice=price;
        for(Coupon c:coupons){
            // if (c.getDeductionType() == DeductionType.PERCENTAGE) {
                // finalPrice = finalPrice * (100 - c.getDiscount()) /100;
            // } else {
                finalPrice =  finalPrice - c.getDiscount();
            // }        
            if(finalPrice<0){
                finalPrice=0;break;
            }
        }
        price=finalPrice;
        return finalPrice;
    }
}


