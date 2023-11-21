public class OnlinePayment implements PaymentCategory{

    Card card;
    
    public OnlinePayment(Card card) {
        this.card = card;
    }

    @Override
    public boolean makePayment(Customer customer, Cart cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makePayment'");
    }
    
}
