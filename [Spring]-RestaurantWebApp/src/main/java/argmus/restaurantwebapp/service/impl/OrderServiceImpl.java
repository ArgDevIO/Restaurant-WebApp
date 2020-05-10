package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.exception.AddressNotFoundOnUserException;
import argmus.restaurantwebapp.exception.MenuProductDoesntExistException;
import argmus.restaurantwebapp.exception.UserDoesntExistException;
import argmus.restaurantwebapp.model.*;
import argmus.restaurantwebapp.repository.*;
import argmus.restaurantwebapp.service.OrderService;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final MenuProductRepository menuProductRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final AddressRepository addressRepository;

    public OrderServiceImpl(UserRepository userRepository, MenuProductRepository menuProductRepository, OrderRepository orderRepository, OrderProductRepository orderProductRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.menuProductRepository = menuProductRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Order newOrder(JsonObject jsonOrder) {
        Order newOrder = new Order();
        List<OrderProduct> orderProducts = new ArrayList<>();

        User user = this.userRepository.findById(jsonOrder.get("user_id").getAsLong()).orElseThrow(() -> new UserDoesntExistException("User doesn't exist"));
        newOrder.setUser(user);

        Address address = this.addressRepository.findById(jsonOrder.get("address_id").getAsLong()).orElseThrow(AddressNotFoundOnUserException::new);
        newOrder.setAddress(address);

        newOrder.setPaymentType(jsonOrder.get("paymentType").getAsString());
        newOrder.setDeliveryPrice(jsonOrder.get("deliveryPrice").getAsInt());
        newOrder.setTotalPrice(jsonOrder.get("totalPrice").getAsInt());

        Order savedOrder = this.orderRepository.save(newOrder);

        jsonOrder.getAsJsonArray("orderProducts").iterator().forEachRemaining(jsonElement -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(savedOrder);

            MenuProduct product = this.menuProductRepository.findById(jsonElement.getAsJsonObject().get("product_id").getAsLong()).orElseThrow(MenuProductDoesntExistException::new);
            orderProduct.setProduct(product);
            orderProduct.setComment(jsonElement.getAsJsonObject().get("comment").getAsString());
            orderProduct.setQuantity(jsonElement.getAsJsonObject().get("quantity").getAsInt());

            orderProducts.add(orderProduct);
        });

        this.orderProductRepository.saveAll(orderProducts);

        newOrder.setOrderProducts(null);
        newOrder.getUser().setPassword(null);
        newOrder.getUser().setConfirmPassword(null);
        newOrder.getUser().setAddresses(null);
        newOrder.getUser().setRoles(null);
        return newOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();
        orders.forEach(order -> {
            order.getUser().setAddresses(null);
            order.getUser().setRoles(null);
            order.getUser().setPassword(null);
            order.getUser().setConfirmPassword(null);
            order.getUser().setCreated_At(null);
            order.getUser().setUpdated_At(null);

            order.getAddress().setCreated_At(null);
            order.getAddress().setUpdated_At(null);

            List<OrderProduct> orderProducts = this.orderProductRepository.findOrderProductsByOrder_Id(order.getId());
            if (!orderProducts.isEmpty()) {
                orderProducts.forEach(orderProduct -> {
                    orderProduct.getProduct().setCategory(null);
                    orderProduct.getProduct().setCreated_At(null);
                    orderProduct.getProduct().setUpdated_At(null);
                });

                order.setOrderProducts(orderProducts);
            }
        });

        return orders;
    }
}
