package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.service.MenuCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu/category", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MenuCategoryController {

    private final MenuCategoryService categoryService;

    public MenuCategoryController(MenuCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //TODO POST(/menu/category): create new menu category
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuCategory create(@RequestParam String name,
                               @RequestParam String icon,
                               @RequestParam(defaultValue = "true") boolean active) {
        return this.categoryService.createMenuCategory(name, icon, active);
    }

    //TODO GET(/menu/category): get all menu categories
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuCategory> getAll() {
        return this.categoryService.getAllMenuCategories();
    }

    //TODO GET(/menu/category/{id}/products): get all products by category id
    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuProduct> getAllProducts(@PathVariable int id) {
        return this.categoryService.getAllProducts(id);
    }

    //TODO GET(/menu/category/{id}): get menu category by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuCategory get(@PathVariable int id) {
        return this.categoryService.getMenuCategory(id);
    }

    //TODO DELETE(/menu/category/{id}): delete menu category by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        this.categoryService.deleteMenuCategory(id);
    }

    //TODO PUT(/menu/category/{id}): update menu category by id
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuCategory update(@PathVariable int id,
                               @RequestParam String name,
                               @RequestParam String icon,
                               @RequestParam(defaultValue = "true") boolean active) {
        return this.categoryService.updateMenuCategory(id, name, icon, active);
    }

}
