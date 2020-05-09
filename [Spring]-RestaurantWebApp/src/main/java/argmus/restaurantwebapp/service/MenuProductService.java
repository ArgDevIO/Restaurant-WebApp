package argmus.restaurantwebapp.service;

import argmus.restaurantwebapp.model.MenuProduct;

import java.util.List;

public interface MenuProductService {

    MenuProduct createMenuProduct(MenuProduct product);

    List<MenuProduct> getAllMenuProducts();

    MenuProduct getMenuProduct(Long id);

    void deleteMenuProduct(Long id);

    MenuProduct updateMenuProduct(Long id, MenuProduct product);
}
