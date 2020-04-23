package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.MenuProduct;

import java.util.List;

public interface MenuProductService {
    MenuProduct createMenuProduct(String name, String description, int price, int categoryId);

    List<MenuProduct> getAllMenuProducts();
}
