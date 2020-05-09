package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.MenuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MenuProductRepository extends JpaRepository<MenuProduct, Long> {
    List<MenuProduct> findMenuProductsByCategory_Id(Long id);
    int countMenuProductsByCategory_Id(Long id);
}