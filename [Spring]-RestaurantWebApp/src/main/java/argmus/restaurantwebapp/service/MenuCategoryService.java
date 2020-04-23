package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.MenuCategory;

public interface MenuCategoryService {

    MenuCategory createMenuCategory(String name, String icon);
}