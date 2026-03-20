import java.util.List;

import managers.OrderManager;
import managers.RestaurantManager;
import models.Cart;
import models.MenuItem;
import models.Order;
import models.Restaurant;
import models.User;
import services.NotificationService;
import strategies.PaymentStrategy;

public class ZomatoApp {
    public ZomatoApp() {
        initializeRestaurants();
    }

    public void initializeRestaurants() {
        Restaurant restaurant1 = new Restaurant("Bikaner", "Delhi");
        restaurant1.addMenuItem(new MenuItem("P1", "Chole Bhature", 120));
        restaurant1.addMenuItem(new MenuItem("P2", "Samosa", 15));

        Restaurant restaurant2 = new Restaurant("Haldiram", "Kolkata");
        restaurant2.addMenuItem(new MenuItem("P1", "Raj Kachori", 80));
        restaurant2.addMenuItem(new MenuItem("P2", "Pav Bhaji", 100));
        restaurant2.addMenuItem(new MenuItem("P3", "Dhokla", 50));

        Restaurant restaurant3 = new Restaurant("Saravana Bhavan", "Chennai");
        restaurant3.addMenuItem(new MenuItem("P1", "Masala Dosa", 90));
        restaurant3.addMenuItem(new MenuItem("P2", "Idli Vada", 60));
        restaurant3.addMenuItem(new MenuItem("P3", "Filter Coffee", 30));

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(restaurant1);
        restaurantManager.addRestaurant(restaurant2);
        restaurantManager.addRestaurant(restaurant3);
    }

    public List<Restaurant> searchRestaurants(String location) {
        return RestaurantManager.getInstance().searchByLocation(location);
    }

    public void selectRestaurant(User user, Restaurant restaurant) {
        // System.out.println(user.getName() + "---" + restaurant.getName());
        Cart cart = user.getCart();
        // System.out.println("-------------");
        cart.setRestaurant(restaurant);
    }

    public void addToCart(User user, String itemCode) {
        Restaurant restaurant = user.getCart().getRestaurant();
        if (restaurant == null) {
            System.out.println("Please select a restaurant first.");
            return;
        }
        for (MenuItem item : restaurant.getMenu()) {
            if (item.getId().equals(itemCode)) {
                user.getCart().addItem(item);
                break;
            }
        }

    }

    public Order checkoutNow(User user, String orderType, PaymentStrategy paymentStrategy) {
        return checkout(user, orderType, paymentStrategy, new NowOrderFactory());
    }

    public Order checkoutScheduled(User user, String orderType, PaymentStrategy paymentStrategy, String scheduleTime) {
        return checkout(user, orderType, paymentStrategy, new ScheduledOrderFactory(scheduleTime));
    }

    public Order checkout(User user, String orderType, PaymentStrategy paymentStrategy, OrderFactory orderFactory) {
        if (user.getCart().isEmpty())
            return null;

        Cart cart = user.getCart();
        Restaurant restaurant = cart.getRestaurant();
        List<MenuItem> itemList = cart.getItems();
        double totalCost = cart.getTotalCost();

        Order order = orderFactory.createOrder(user, restaurant, itemList, orderType, paymentStrategy, totalCost);
        OrderManager.getInstance().addOrder(order);
        return order;

    }

    public void payForOrder(User user, Order order) {
        boolean isPaymentSuccess = order.processPayment();
        if (isPaymentSuccess) {
            NotificationService.notify(order);
            user.getCart().clear();
        }
    }

    public void printUserCart(User user) {
        System.out.println("Items in cart:");
        System.out.println("------------------------------------");
        for (MenuItem item : user.getCart().getItems()) {
            System.out.println(item.getId() + " : " + item.getName() + " : " + item.getPrice());
        }
        System.out.println("------------------------------------");
        System.out.println("Grand total : " + user.getCart().getTotalCost());
    }

}
