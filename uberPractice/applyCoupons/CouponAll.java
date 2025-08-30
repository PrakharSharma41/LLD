import java.util.List;

public class CouponAll extends Coupon{

    CouponAll(double discount) {
        super(discount);
    }
    
    @Override
    void applyCoupon(List<CartItem> items,Coupon coupon) {
        for(CartItem item:items){
            if(item.isCoupon())continue;
            Product product=(Product)item;
            product.addCoupon(coupon);
        }
    }

    // @Override
    // public DeductionType getDeductionType() {
    //     return DeductionType.FIXED;
    // }
    
}

// p1 p2 c1 p3 p4