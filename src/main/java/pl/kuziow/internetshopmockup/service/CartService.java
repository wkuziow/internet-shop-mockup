package pl.kuziow.internetshopmockup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kuziow.internetshopmockup.exception.ProductNotFoundException;
import pl.kuziow.internetshopmockup.exception.UserNotFoundException;
import pl.kuziow.internetshopmockup.model.*;
import pl.kuziow.internetshopmockup.repository.CartRepository;
import pl.kuziow.internetshopmockup.repository.ProductRepository;
import pl.kuziow.internetshopmockup.repository.UserRepository;

import java.util.ArrayList;

@Service
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;

    public CartService(CartRepository cartRepository, ProductRepository productRepository,
                       UserRepository userRepository, PaymentService paymentService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.paymentService = paymentService;
    }

    @Transactional
    public void processCartRequest(CartRequest cartRequest) {
        var cart = createCart(cartRequest);
        log.info("Cart was created: [{}]", cart);
        var savedCart = cartRepository.save(cart);
        log.info("Cart saved in the DB with id: [{}]", savedCart.getId());

        var paymentMethod = cartRequest.getPayment().getPaymentMethod();
        log.info("About to proceed with payment method: {}", paymentMethod);
        paymentService.paymentOrchestrator(savedCart, paymentMethod);
    }

    private Cart createCart(CartRequest cartRequest) {
        var userId = Long.parseLong(cartRequest.getCustomerId());
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with " +
                "ID: " + userId));
        log.info("User for this car was found with id: {}", userId);

        return Cart.builder().user(user).cartItems(getCartItems(cartRequest)).build();
    }

    private ArrayList<CartItem> getCartItems(CartRequest cartRequest) {
        var cartItems = new ArrayList<CartItem>();

        for (var entry : cartRequest.getProducts().entrySet()) {
            var productId = Long.parseLong(entry.getKey());
            var product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(
                    "Product not found with ID: " + productId));
            var cartItem = CartItem.builder().product(product).quantity(entry.getValue()).build();
            cartItems.add(cartItem);
        }
        log.info("CartItems we found in the CartRequest: {}", cartItems);
        return cartItems;
    }
}
