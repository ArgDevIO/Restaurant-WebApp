package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.MenuCategory;

import java.util.List;

public interface MenuCategoryService {

    MenuCategory createMenuCategory(String name, String icon, boolean active);

    List<MenuCategory> getAllMenuCategories();

    MenuCategory getMenuCategory(int id);

    void deleteMenuCategory(int id);

    MenuCategory updateMenuCategory(int id, String name, String icon, boolean active);
}