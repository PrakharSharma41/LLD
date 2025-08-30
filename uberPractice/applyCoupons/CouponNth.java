import java.util.List;

public class CouponNth extends Coupon{
    ProductType type;
    int offset;
    CouponNth(double discount,int offset,ProductType type) {
        super(discount);
        this.offset=offset;
        this.type=type;
    }

    // D% off on Nth item of Type T.
    
    @Override
    void applyCoupon(List<CartItem> items, Coupon coupon) {
        int index=0;
        for(CartItem item:items){
            if(item.isCoupon())continue;
            Product product=(Product)item;
            if(product.productType==type)index++;
            if(index==offset){
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
