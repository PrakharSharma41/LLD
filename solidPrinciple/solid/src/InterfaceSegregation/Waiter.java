package solidPrinciple.solid.src.InterfaceSegregation;

public class Waiter implements RestaurantEmployee{


    // here we are unnecessarily implementing serveCustomers and cookFood for waiter class
    @Override
    public void washDishes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'washDishes'");
    }

    @Override
    public void serveCustomers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serveCustomers'");
    }

    @Override
    public void cookFood() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cookFood'");
    }
    
}
