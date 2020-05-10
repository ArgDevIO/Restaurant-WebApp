package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUser_Id(Long id);
}
