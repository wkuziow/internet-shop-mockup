package pl.kuziow.internetshopmockup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kuziow.internetshopmockup.model.CartRequest;
import pl.kuziow.internetshopmockup.service.CartService;

@RestController
@Slf4j
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> receiveCart(@RequestBody CartRequest cartRequest) {
        log.info("Received cart request: {}", cartRequest);
        try {
            cartService.processCartRequest(cartRequest);
            log.info("About to send HTTP 200");
            return ResponseEntity.ok("Cart request processed successfully");
        } catch (Exception e) {
            log.error("Error processing cart request: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing cart request");
        }
    }

}
