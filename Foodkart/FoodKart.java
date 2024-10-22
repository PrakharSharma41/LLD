package Foodkart;
import java.util.*;

public class FoodKart {
    List<Customer>customers=new ArrayList<>();
    List<Owner>owners=new ArrayList<>();

    List<Restaurant> restaurants=new ArrayList<>();
    User loggedInUser=null;
    RestaurantGetter resGetter;
    public void logIn(User user){
        this.loggedInUser=user;
    }
    public List<Customer> getCustomers(){
        return customers;
    }
    public List<Owner> getOwners(){
        return owners;
    }
    public void registerCustomer(String name,String gender, String phone, String pincode){
        Customer user=new Customer(name, gender, phone, pincode);
        customers.add(user);
    }
    public void registerOwner(String name,String gender, String phone, String pincode){
        Owner user=new Owner(name, gender, phone, pincode);
        owners.add(user);
    }

    public void registerRestaurant(String restaurantName,List<String>pincodes, Food food, Integer foodPrice,Integer foodQuantity){
        if(this.loggedInUser==null || this.loggedInUser.userType!=UserType.OWNER){
            System.out.println("owner not logged in");
        }
        Restaurant restaurant=new Restaurant();
        restaurant.setPincodes(pincodes);
        restaurant.setRestaurantName(restaurantName);
        restaurant.dishManager.setFood(new ArrayList<Food>(Arrays.asList(food)));
        // if(loggedInUser.userType==UserType.OWNER){
        //     restaurant.setUser(loggedInUser);
        // }

        restaurants.add(restaurant);
        
    }
    public void updateQuantity(String restaurantName,int foodQuantity){
        if(loggedInUser==null){
            System.out.println("owner not logged in");
            return ;
        }
        if(loggedInUser.userType==UserType.OWNER){
            Restaurant res=null;
            for(int i=0;i<restaurants.size();i++){
                if(restaurants.get(i).restaurantName.equals(restaurantName)){
                    res=restaurants.get(i);break;
                }
            }
            if(res!=null){
                List<Food> food=res.dishManager.getFood();
                if(food.size()>0){
                    food.get(0).setInitialQuantity(foodQuantity);
                }
                System.out.println("food quantity is now"+food.get(0).getInitialQuantity());
            }
        }
    }
    public Restaurant getByName(String restaurantName){
        Restaurant res=null;
        for(int i=0;i<restaurants.size();i++){
            if(restaurants.get(i).restaurantName.equals(restaurantName)){
                res=restaurants.get(i);break;
            }
        }
        return res;
    }
    public void rate_restaurant(String restaurantName,Integer rating, String comments){
        Restaurant res=getByName(restaurantName);
        if(res!=null){
            if(rating>=1&&rating<=5){
                res.rateManager.rate(rating,comments);
            }else{
                System.out.println("wrong rating provided");
            }
        }
    }
    public void rate_restaurant(Restaurant res,Integer rating){
        if(res!=null){
            if(rating>=1&&rating<=5){
                res.rateManager.rate(rating);
            }else{
                System.out.println("wrong rating provided");
            }
        } 
    }
    public List<Restaurant> showRestaurant(ResGetterType type){
        resGetter=ResGetterFactory.get(type);
        return resGetter.getRestaurant(restaurants);
    }
    public void createOrder(Restaurant restaurant,int quantity){
        if(this.loggedInUser==null){
            System.out.println("no user logged in");
            return ;
        }
        if(this.loggedInUser.userType==UserType.CUSTOMER){
            Order order=new Order();
            order.setCustomer((Customer)this.loggedInUser);
            order.setRestaurant(restaurant);
            order.setQuantity(quantity);
            order.setFood(restaurant.getDishManager().getFood().get(0));
            order.createOrder();

            return ;
        }
    }
    public void logout(){
        this.loggedInUser=null;
    }

}
