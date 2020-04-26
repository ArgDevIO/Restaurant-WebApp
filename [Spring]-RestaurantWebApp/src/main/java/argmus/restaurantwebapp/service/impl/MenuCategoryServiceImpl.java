package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.model.exceptions.MenuCategoryDoesntExistException;
import argmus.restaurantwebapp.repository.MenuCategoryRepository;
import argmus.restaurantwebapp.repository.MenuProductRepository;
import argmus.restaurantwebapp.service.MenuCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryRepository categoryRepository;
    private final MenuProductRepository productRepository;

    public MenuCategoryServiceImpl(MenuCategoryRepository categoryRepository, MenuProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public MenuCategory createMenuCategory(String name, String icon, boolean active) {
        return this.categoryRepository.save(new MenuCategory(name, icon, active));
    }

    @Override
    public List<MenuCategory> getAllMenuCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public MenuCategory getMenuCategory(int id) {
        return this.categoryRepository.findById(id).orElseThrow(MenuCategoryDoesntExistException::new);
    }

    @Override
    public void deleteMenuCategory(int id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public MenuCategory updateMenuCategory(int id, String name, String icon, boolean active) {
        MenuCategory category = this.categoryRepository.findById(id).orElseThrow(MenuCategoryDoesntExistException::new);
        MenuCategory newCategory = category.toBuilder().build();

        newCategory.setName(name);
        newCategory.setIcon(icon);
        newCategory.setActive(active);

        if (!newCategory.equals(category))
            return this.categoryRepository.save(newCategory);
        return category;
    }

    @Override
    public List<MenuProduct> getAllProducts(int id) {
        return this.productRepository.findMenuProductsByCategory_Id(id);
    }
}
