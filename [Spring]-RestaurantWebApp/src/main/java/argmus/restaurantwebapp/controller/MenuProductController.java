package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.service.MenuProductService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu/product", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MenuProductController {

    private final MenuProductService productService;

    public MenuProductController(MenuProductService productService) {
        this.productService = productService;
    }

    //TODO POST(/menu/product): create new menu product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuProduct create(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam int price,
                              @RequestParam int categoryId) {
        return this.productService.createMenuProduct(name, description, price, categoryId);
    }

    //TODO GET(/menu/product): get all menu products
    @GetMapping
    public List<MenuProduct> getAll(){
        return this.productService.getAllMenuProducts();
    }

    //TODO GET(/menu/product/{id}): get menu product by id
    @GetMapping("/{id}")
    public MenuProduct get(@PathVariable int id){
        return this.productService.getProduct(id);
    }

    //TODO DELETE(/menu/product/{id}): delete menu product by id

    //TODO PUT(/menu/product/{id}): update menu product by id

}
