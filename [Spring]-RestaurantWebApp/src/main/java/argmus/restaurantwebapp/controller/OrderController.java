package argmus.restaurantwebapp.controller;

import argmus.restaurantwebapp.service.MapValidationErrorService;
import argmus.restaurantwebapp.service.OrderService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import static argmus.restaurantwebapp.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping(value = "/api/orders", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;
    private final MapValidationErrorService mapValidationErrorService;

    public OrderController(OrderService orderService, MapValidationErrorService mapValidationErrorService) {
        this.orderService = orderService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newOrder(@RequestBody String jsonString) {
        JsonObject jsonOrder = JsonParser.parseString(jsonString).getAsJsonObject();

        return new ResponseEntity<>(this.orderService.newOrder(jsonOrder), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(this.orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllOrdersByUser(@PathVariable Long userId,
                                                @RequestHeader(HEADER_STRING) String token) {
        return new ResponseEntity<>(this.orderService.getAllOrdersByUser(userId, token), HttpStatus.OK);
    }
}
