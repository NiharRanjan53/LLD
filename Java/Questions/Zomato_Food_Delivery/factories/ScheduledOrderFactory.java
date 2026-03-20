import java.util.List;

import models.DeliveryOrder;
import models.MenuItem;
import models.Order;
import models.PickupOrder;
import models.Restaurant;
import models.User;
import strategies.PaymentStrategy;
import utils.TimeUtils;

public class ScheduledOrderFactory implements OrderFactory {
    private String scheduleTime;

    public ScheduledOrderFactory(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public Order createOrder(User user, Restaurant restaurant, List<MenuItem> menuItems, String orderType,
            PaymentStrategy paymentStrategy, double totalCost) {
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
        order.setTotal(totalCost);
        order.setScheduled(scheduleTime);
        return order;
    }

}
