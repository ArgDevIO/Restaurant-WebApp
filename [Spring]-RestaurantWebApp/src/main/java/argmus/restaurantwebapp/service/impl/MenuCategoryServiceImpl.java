package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.exceptions.MenuCategoryDoesntExistException;
import argmus.restaurantwebapp.repository.MenuCategoryRepository;
import argmus.restaurantwebapp.service.MenuCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

    private final MenuCategoryRepository categoryRepository;

    public MenuCategoryServiceImpl(MenuCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public MenuCategory createMenuCategory(String name, String icon) {
        return this.categoryRepository.save(new MenuCategory(name, icon));
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
    public MenuCategory updateMenuCategory(int id, String name, String icon) {
        MenuCategory category = this.categoryRepository.findById(id).orElseThrow(MenuCategoryDoesntExistException::new);

        if (!name.isBlank()) category.setName(name);
        if (!icon.isBlank()) category.setIcon(icon);

        return this.categoryRepository.save(category);
    }
}
