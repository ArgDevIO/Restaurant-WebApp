package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.exception.MenuCategoryDoesntExistException;
import argmus.restaurantwebapp.exception.MenuProductDoesntExistException;
import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
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
    public MenuProduct getMenuProduct(Long id) {
        return this.productRepository.findById(id).orElseThrow(MenuProductDoesntExistException::new);
    }

    @Override
    public void deleteMenuProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public MenuProduct updateMenuProduct(Long id, MenuProduct product) {
        MenuProduct existingProduct = this.productRepository.findById(product.getId()).orElseThrow(MenuProductDoesntExistException::new);

        MenuCategory category = this.categoryRepository.findById(product.getCategory().getId()).orElseThrow(MenuCategoryDoesntExistException::new);
        product.setCategory(category);

        if (!existingProduct.equals(product))
            return this.productRepository.save(product);
        return existingProduct;
    }
}
