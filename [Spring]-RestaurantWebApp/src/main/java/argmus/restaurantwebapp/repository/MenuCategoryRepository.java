package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}