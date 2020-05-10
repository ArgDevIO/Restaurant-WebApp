package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.Order;
import com.google.gson.JsonObject;

import java.util.List;

public interface OrderService {
    Order newOrder(JsonObject jsonOrder);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUser(Long userId, String token);
}
