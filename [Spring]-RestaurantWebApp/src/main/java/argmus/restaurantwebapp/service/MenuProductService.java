package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.MenuProduct;

public interface MenuProductService {
    MenuProduct createMenuProduct(String name, String description, int price, int categoryId);
}
