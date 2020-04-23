package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.model.exceptions.MenuCategoryDoesntExistException;
import argmus.restaurantwebapp.repository.MenuCategoryRepository;
import argmus.restaurantwebapp.repository.MenuProductRepository;
import argmus.restaurantwebapp.service.MenuProductService;
import org.springframework.stereotype.Service;

@Service
public class MenuProductServiceImpl implements MenuProductService {

    private final MenuProductRepository productRepository;
    private final MenuCategoryRepository categoryRepository;

    public MenuProductServiceImpl(MenuProductRepository productRepository, MenuCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public MenuProduct createMenuProduct(String name, String description, int price, int categoryId) {
        MenuCategory category = this.categoryRepository.findById(categoryId).orElseThrow(MenuCategoryDoesntExistException::new);

        return this.productRepository.save(new MenuProduct(name, description, price, category));
    }
}
