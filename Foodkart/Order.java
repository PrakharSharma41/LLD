package Foodkart;

public class Order {
    Restaurant restaurant;
    Customer customer;
    Food food;
    int quantity;
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }
    public void createOrder(){
        if(quantity>restaurant.getDishManager().getFood().get(0).getInitialQuantity()){
            System.out.println("restaurant does not have this much quantity, order not placed");
            return ;
        }
        System.out.println("food.getInitialQuantity() is "+food.getInitialQuantity()+" "+quantity);
        food.setInitialQuantity(-quantity);
    }
}
