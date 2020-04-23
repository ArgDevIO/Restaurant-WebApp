package argmus.restaurantwebapp.controller;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/menu/product", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MenuProductController {

    //TODO POST(/menu/product): create new menu product

    //TODO GET(/menu/product): get all menu products

    //TODO GET(/menu/product/{id}): get menu product by id

    //TODO DELETE(/menu/product/{id}): delete menu product by id

    //TODO PUT(/menu/product/{id}): update menu product by id

}
