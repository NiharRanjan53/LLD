import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import models.DeliveryOrder;
import models.MenuItem;
import models.Order;
import models.PickupOrder;
import models.Restaurant;
import models.User;
import strategies.PaymentStrategy;
import utils.TimeUtils;

public class NowOrderFactory implements OrderFactory {

    @Override
    public Order createOrder(User user, Restaurant restaurant, List<MenuItem> menuItems, String orderType,
            PaymentStrategy paymentStrategy, double getTotalCost) {
        Order order = null;

        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else {
            PickupOrder PickupOrder = new PickupOrder();
            PickupOrder.setRestaurantAddress(restaurant.getAddress());
            order = PickupOrder;
        }
        order.setUser(user);
        order.setItems(menuItems);
        order.setPaymentStrategy(paymentStrategy);
        order.setRestaurant(restaurant);
        order.setTotal(getTotalCost);
        order.setScheduled(TimeUtils.getCurrentTime());
        return order;
    }

}
