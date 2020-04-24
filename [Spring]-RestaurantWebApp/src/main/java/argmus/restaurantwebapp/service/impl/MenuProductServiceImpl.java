package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.model.exceptions.MenuCategoryDoesntExistException;
import argmus.restaurantwebapp.model.exceptions.MenuProductDoesntExistException;
import argmus.restaurantwebapp.repository.MenuCategoryRepository;
import argmus.restaurantwebapp.repository.MenuProductRepository;
import argmus.restaurantwebapp.service.MenuProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuProductServiceImpl implements MenuProductService {

    private final MenuProductRepository productRepository;
    private final MenuCategoryRepository categoryRepository;

    public MenuProductServiceImpl(MenuProductRepository productRepository, MenuCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public MenuProduct createMenuProduct(String name, String description, int price, boolean active, int categoryId) {
        MenuCategory category = this.categoryRepository.findById(categoryId).orElseThrow(MenuCategoryDoesntExistException::new);

        return this.productRepository.save(new MenuProduct(name, description, price, active, category));
    }

    @Override
    public List<MenuProduct> getAllMenuProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public MenuProduct getMenuProduct(int id) {
        return this.productRepository.findById(id).orElseThrow(MenuProductDoesntExistException::new);
    }

    @Override
    public void deleteMenuProduct(int id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public MenuProduct updateMenuProduct(int id, String name, String description, int price, boolean active, int categoryId) {
        MenuProduct product = this.productRepository.findById(id).orElseThrow(MenuProductDoesntExistException::new);
        MenuCategory category = this.categoryRepository.findById(categoryId).orElseThrow(MenuCategoryDoesntExistException::new);

        if (!name.isBlank()) product.setName(name);
        if (!description.isBlank()) product.setDescription(description);
        if (product.getPrice() != price) product.setPrice(price);
        if (product.getCategory() != category) product.setCategory(category);

        return this.productRepository.save(product);
    }
}
