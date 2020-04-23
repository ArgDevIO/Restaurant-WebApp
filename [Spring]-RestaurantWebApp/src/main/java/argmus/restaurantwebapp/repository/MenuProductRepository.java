package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.MenuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuProductRepository extends JpaRepository<MenuProduct, Integer> {
}