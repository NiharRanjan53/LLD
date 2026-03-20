import models.Order;
import models.Restaurant;
import models.User;
import strategies.UPIPaymentStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ZomatoApp app = new ZomatoApp();

        // Simulate a user coming in (Happy Flow)
        User user = new User(101, "Aditya", "Delhi");
        System.out.println("User: " + user.getName() + " is active.");

        // User searches for restaurants by location
        List<Restaurant> restaurantList = app.searchRestaurants("Delhi");

        if (restaurantList.isEmpty()) {
            System.out.println("No restaurants found!");
            return;
        }

        System.out.println("Found Restaurants:");
        for (Restaurant restaurant : restaurantList) {
            System.out.println(" - " + restaurant.getName());
        }

        // User selects a restaurant
        app.selectRestaurant(user, restaurantList.get(0));
        System.out.println("Selected restaurant: " + restaurantList.get(0).getName());

        // User adds items to the cart
        app.addToCart(user, "P1");
        app.addToCart(user, "P2");

        app.printUserCart(user);

        // User checkout the cart
        Order order = app.checkoutNow(user, "Delivery", new UPIPaymentStrategy("1234567890"));

        app.payForOrder(user, order);

    }
}
