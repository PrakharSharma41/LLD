import java.util.List;

public class CouponNext extends Coupon{

    CouponNext(double discount) {
        super(discount);
    }

    @Override
    void applyCoupon(List<CartItem> items, Coupon coupon) {
        boolean paramCouponFound=false;
        for(CartItem item:items){
            if(item.isCoupon()){
                if(coupon==(Coupon)item)paramCouponFound=true;
                continue;
            }
            Product product=(Product)item;
            if(paramCouponFound){
                product.addCoupon(coupon);
                break;
            }
        }
    }    

    // @Override
    // public DeductionType getDeductionType() {
    //     return DeductionType.FIXED;
    // }

}
