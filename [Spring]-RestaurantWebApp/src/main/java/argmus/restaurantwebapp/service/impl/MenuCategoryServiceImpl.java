package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.repository.MenuCategoryRepository;
import argmus.restaurantwebapp.service.MenuCategoryService;
import org.springframework.stereotype.Service;

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
}
