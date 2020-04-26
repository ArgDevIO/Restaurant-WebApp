package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.MenuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MenuProductRepository extends JpaRepository<MenuProduct, Integer> {
    List<MenuProduct> findMenuProductsByCategory_Id(int id);
    int countMenuProductsByCategory_Id(int id);
}