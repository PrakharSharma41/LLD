import java.util.List;

public abstract class Coupon implements CartItem{
    double discount;

    Coupon(double discount){
        this.discount=discount;
    }
    @Override
    public boolean isCoupon() {
        return true;
    }
    abstract void applyCoupon(List<CartItem>items,Coupon coupon);
    // public abstract DeductionType getDeductionType(); 
    double getDiscount(){
        return discount;
    }
}
