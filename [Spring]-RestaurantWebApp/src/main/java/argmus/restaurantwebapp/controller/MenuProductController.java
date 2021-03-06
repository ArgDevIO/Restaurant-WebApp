package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.model.MenuProduct;
import argmus.restaurantwebapp.service.MapValidationErrorService;
import argmus.restaurantwebapp.service.MenuProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/menu/products", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class MenuProductController {

    private final MenuProductService productService;
    private final MapValidationErrorService mapValidationErrorService;

    public MenuProductController(MenuProductService productService, MapValidationErrorService mapValidationErrorService) {
        this.productService = productService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    //TODO POST(/api/menu/products): create new menu product
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MenuProduct product, BindingResult result) {
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(this.productService.createMenuProduct(product), HttpStatus.CREATED);
    }

    //TODO GET(/api/menu/products): get all menu products
    @GetMapping
    public List<MenuProduct> getAll() {
        return this.productService.getAllMenuProducts();
    }

    //TODO GET(/api/menu/products/{id}): get menu product by id
    @GetMapping("/{id}")
    public MenuProduct get(@PathVariable Long id) {
        return this.productService.getMenuProduct(id);
    }

    //TODO DELETE(/api/menu/products/{id}): delete menu product by id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.deleteMenuProduct(id);
    }

    //TODO PUT(/api/menu/products/{id}): update menu product by id
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody MenuProduct product,
                                    BindingResult result) {
        ResponseEntity<?> errorMap = this.mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(this.productService.updateMenuProduct(id, product), HttpStatus.OK);
    }
}
