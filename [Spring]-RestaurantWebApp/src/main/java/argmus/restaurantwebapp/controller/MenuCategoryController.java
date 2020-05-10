package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.MenuCategory;
import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.service.MapValidationErrorService;
import argmus.restaurantwebapp.service.MenuCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/menu/categories", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class MenuCategoryController {

    private final MenuCategoryService categoryService;
    private final MapValidationErrorService mapValidationErrorService;

    public MenuCategoryController(MenuCategoryService categoryService, MapValidationErrorService mapValidationErrorService) {
        this.categoryService = categoryService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    //TODO POST(/api/menu/categories): create new menu category
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MenuCategory category, BindingResult result) {
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(this.categoryService.createMenuCategory(category), HttpStatus.CREATED);
    }

    //TODO POST(/api/menu/categories/{id}/transfer): transfer all products from category x to category y
    @PostMapping("/{fromId}/transfer")
    public List<MenuProduct> transferProducts(@PathVariable Long fromId,
                                              @RequestParam Long toId) {
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
    public MenuCategory get(@PathVariable Long id) {
        return this.categoryService.getMenuCategory(id);
    }

    //TODO GET(/api/menu/categories/{id}/products): get all products by category id
    @GetMapping("/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuProduct> getAllProducts(@PathVariable Long id) {
        return this.categoryService.getAllProducts(id);
    }

    //TODO DELETE(/api/menu/categories/{id}): delete menu category by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.categoryService.deleteMenuCategory(id);
    }

    //TODO PUT(/api/menu/categories/{id}): update menu category by id
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody MenuCategory category,
                                    BindingResult result) {
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(this.categoryService.updateMenuCategory(id, category), HttpStatus.OK);
    }

}
