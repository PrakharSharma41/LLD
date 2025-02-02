package applyCoupons;

public class Item2 extends Product{
    Item2(String name,double originalPrice,ProductType type){
        super(name, originalPrice, type);
    }
    public double getPrice(){
        return originalPrice;
    }
}
