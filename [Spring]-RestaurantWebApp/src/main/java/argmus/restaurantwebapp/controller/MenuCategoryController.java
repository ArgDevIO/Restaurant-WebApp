package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.service.MenuCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/menu/categories", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MenuCategoryController {

    private final MenuCategoryService categoryService;

    public MenuCategoryController(MenuCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //TODO POST(/api/menu/categories): create new menu category
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuCategory create(@RequestParam String name,
                               @RequestParam String icon,
                               @RequestParam(defaultValue = "true") boolean active) {
        return this.categoryService.createMenuCategory(name, icon, active);
    }

    //TODO POST(/api/menu/categories/{id}/transfer): transfer all products from category x to category y
    @PostMapping("/{fromId}/transfer")
    public List<MenuProduct> transferProducts(@PathVariable int fromId,
                                              @RequestParam int toId) {
        return this.categoryService.transferProducts(fromId, toId);
    }

    //TODO GET(/api/menu/categories): get all menu categories
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuCategory> getAll() {
        return this.categoryService.getAllMenuCategories();
    }

    //TODO GET(/api/menu/categories/{id} ): get menu category by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuCategory get(@PathVariable int id) {
        return this.categoryService.getMenuCategory(id);
    }

    //TODO GET(/api/menu/categories/{id}/products): get all products by category id
    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuProduct> getAllProducts(@PathVariable int id) {
        return this.categoryService.getAllProducts(id);
    }

    //TODO DELETE(/api/menu/categories/{id}): delete menu category by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        this.categoryService.deleteMenuCategory(id);
    }

    //TODO PUT(/api/menu/categories/{id}): update menu category by id
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuCategory update(@PathVariable int id,
                               @RequestParam String name,
                               @RequestParam String icon,
                               @RequestParam(defaultValue = "true") boolean active) {
        return this.categoryService.updateMenuCategory(id, name, icon, active);
    }

}
