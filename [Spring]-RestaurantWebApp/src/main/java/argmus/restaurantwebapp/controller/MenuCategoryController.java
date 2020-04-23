package argmus.restaurantwebapp.controller;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/menu/category", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MenuCategoryController {

    //TODO POST(/menu/category): create new menu category

    //TODO GET(/menu/category): get all menu categories

    //TODO GET(/menu/category/{id}/products): get all products by category id

    //TODO GET(/menu/category/{id}): get menu category by id

    //TODO DELETE(/menu/category/{id}): delete menu category by id

    //TODO PUT(/menu/category/{id}): update menu category by id

}
