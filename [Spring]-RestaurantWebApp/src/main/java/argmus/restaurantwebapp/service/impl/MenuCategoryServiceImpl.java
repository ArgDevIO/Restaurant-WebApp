package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.exception.MenuCategoryDoesntExistException;
import argmus.restaurantwebapp.exception.MenuCategoryNotEmptyException;
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
        if (this.productRepository.countMenuProductsByCategory_Id(id) != 0)
            throw new MenuCategoryNotEmptyException("This category can't be deleted because it has products in it, " +
                                                    "to delete it, please first transfer all products to another category!");
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

    @Override
    public List<MenuProduct> transferProducts(int fromId, int toId) {
        List<MenuProduct> products = this.productRepository.findMenuProductsByCategory_Id(fromId);
        MenuCategory newCategory = this.categoryRepository.findById(toId).orElseThrow(MenuCategoryDoesntExistException::new);

        products.forEach(p -> p.setCategory(newCategory));
        return this.productRepository.saveAll(products);
    }
}
