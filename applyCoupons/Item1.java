package applyCoupons;

public class Item1 extends Product{
    Item1(String name,double originalPrice,ProductType type){
        super(name, originalPrice, type);
    }
    public double getPrice(){
        return originalPrice;
    }
}
