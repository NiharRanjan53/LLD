package managers;

import java.util.ArrayList;
import java.util.List;

import models.Restaurant;

public class RestaurantManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantManager instance = null;

    private RestaurantManager() {
    };

    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();

        }
        return instance;
    }

    public void addRestaurant(Restaurant resturant) {
        restaurants.add(resturant);
    }

    public List<Restaurant> searchByLocation(String location) {
        List<Restaurant> result = new ArrayList<>();
        location = location.toLowerCase();
        for (Restaurant r : restaurants) {
            String r1 = r.getAddress().toLowerCase();
            if (r1.equals(location)) {
                result.add(r);
            }
        }
        return result;

    }

    void listRestaurants() {
        System.out.println("\n--- All Restaurants ---");
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant.getName());
        }
    }

}
