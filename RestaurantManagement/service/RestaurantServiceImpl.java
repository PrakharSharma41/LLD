package RestaurantManagement.service;

import java.util.List;

import RestaurantManagement.dao.Restaurantdao;
import RestaurantManagement.dao.UserDao;
import RestaurantManagement.model.Food;
import RestaurantManagement.model.Restaurant;
import RestaurantManagement.strategy.DisplayFactory;
import RestaurantManagement.strategy.RestaurantDisplayStrategy;

public class RestaurantServiceImpl implements RestaurantService{
    Restaurantdao restaurantdao;
    UserDao userDao;
    public RestaurantServiceImpl() {
        restaurantdao=new Restaurantdao();
        userDao=UserDao.getInstance();
    }

    public void registerRestaurant(String restaurantName,List<String>pincodes,String foodname,Integer foodQuantity,Double foodPrice){
        if (restaurantName == null || foodQuantity <= 0) {
            throw new IllegalArgumentException("Invalid restaurant name or quantity.");
        }        
        Food food=new Food(foodname, foodQuantity, foodPrice);
        Restaurant restaurant=new Restaurant(restaurantName, pincodes, food);
        restaurantdao.registerRestaurant(restaurant);
    }

    @Override
    public void updateQuantity(String restaurantName, Integer quantityToAdd) {
        if (restaurantName == null || quantityToAdd == null || quantityToAdd <= 0) {
            throw new IllegalArgumentException("Invalid restaurant name or quantity.");
        }
        restaurantdao.updateQuantity(restaurantName,quantityToAdd);
    }

    @Override
    public void rateRestaurant(String restaurantName, Integer rating, String comment) {
        Restaurant restaurant=restaurantdao.getRestaurant(restaurantName);
        restaurant.updateRating(rating,comment);
    }

    @Override
    public List<Restaurant> showRestaurant(String criteria) {
        RestaurantDisplayStrategy strategy=DisplayFactory.getStrategy(criteria);
        if(strategy!=null){
            return strategy.findRestaurants(restaurantdao.getAllRestaurants(), userDao.getCurrentLoginUser());
        }
        return null;
    }
}
