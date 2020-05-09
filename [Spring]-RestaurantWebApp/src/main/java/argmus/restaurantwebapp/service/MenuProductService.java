package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.MenuProduct;

import java.util.List;

public interface MenuProductService {

    MenuProduct createMenuProduct(MenuProduct product);

    List<MenuProduct> getAllMenuProducts();

    MenuProduct getMenuProduct(int id);

    void deleteMenuProduct(int id);

    MenuProduct updateMenuProduct(int id, String name, String description, int price, boolean active, int categoryId);
}
