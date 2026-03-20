import java.util.List;

import models.MenuItem;
import models.Order;
import models.Restaurant;
import models.User;
import strategies.PaymentStrategy;

public interface OrderFactory {
    Order createOrder(User user, Restaurant restaurant, List<MenuItem> menuItems, String orderType,
            PaymentStrategy paymentStrategy, double totalCost);
}