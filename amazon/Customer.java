import java.util.List;

public class Customer extends User{
    Cart cart;
    boolean isPrime;
    List<Order> orders;
    List<Address> addresses;
    List<Card>cards;
    public Customer(String name, String id, String email, Cart cart, boolean isPrime, List<Order> orders,
            List<Address> addresses, List<Card> cards) {
        super(name, id, email);
        this.cart = cart;
        this.isPrime = isPrime;
        this.orders = orders;
        this.addresses = addresses;
        this.cards = cards;
    }
    

}
