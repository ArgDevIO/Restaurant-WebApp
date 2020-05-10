package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findOrderProductsByOrder_Id(Long orderId);
}
