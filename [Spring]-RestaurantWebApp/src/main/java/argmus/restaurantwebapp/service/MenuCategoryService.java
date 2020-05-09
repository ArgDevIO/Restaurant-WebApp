package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;

import java.util.List;

public interface MenuCategoryService {

    MenuCategory createMenuCategory(MenuCategory category);

    List<MenuCategory> getAllMenuCategories();

    MenuCategory getMenuCategory(Long id);

    void deleteMenuCategory(Long id);

    MenuCategory updateMenuCategory(Long id, MenuCategory category);

    List<MenuProduct> getAllProducts(Long id);

    List<MenuProduct> transferProducts(Long fromId, Long toId);
}