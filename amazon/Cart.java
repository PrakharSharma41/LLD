import java.util.Map;

public class Cart {
    Map<Product,Integer> productCount;
    PaymentCategory payment;
    public void addProduct(Product product){

    }   
    public void removeProduct(Product product){

    }
    public boolean placeOrder(){
        boolean status=payment.makePayment(null, null);
        return status;
    }
}
