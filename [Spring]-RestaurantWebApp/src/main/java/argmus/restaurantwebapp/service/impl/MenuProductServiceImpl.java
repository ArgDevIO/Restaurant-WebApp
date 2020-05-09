package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.exception.MenuCategoryDoesntExistException;
import argmus.restaurantwebapp.exception.MenuProductDoesntExistException;
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
    public MenuProduct createMenuProduct(MenuProduct product) {
        MenuCategory category = this.categoryRepository.findById(product.getCategory().getId()).orElseThrow(MenuCategoryDoesntExistException::new);
        product.setCategory(category);

        return this.productRepository.save(product);
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
        MenuProduct newProduct = product.toBuilder().build();
        
        MenuCategory category = this.categoryRepository.findById(categoryId).orElseThrow(MenuCategoryDoesntExistException::new);

        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setActive(active);
        newProduct.setCategory(category);

        if (!newProduct.equals(product))
            return this.productRepository.save(newProduct);
        return product;
    }
}
