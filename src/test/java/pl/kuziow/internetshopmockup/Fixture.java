package pl.kuziow.internetshopmockup;

import pl.kuziow.internetshopmockup.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Fixture {

    public static CartRequest createValidCartRequest() {
        var cartRequest = new CartRequest();
        cartRequest.setCustomerId("1");

        Map<String, Integer> products = new HashMap<>();
        products.put("1", 2);
        products.put("2", 1);
        cartRequest.setProducts(products);

        var paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentMethod("Credit Card");
        cartRequest.setPayment(paymentRequest);

        return cartRequest;
    }

    public static Payment createMockPayment() {
        return Payment.builder()
                .paymentId("mockPaymentId")
                .amount(20.0)
                .paymentMethod("Credit Card")
                .paymentStatus("SUCCESS")
                .cart(createMockCart())
                .build();
    }

    public static User createMockUser() {
        return User.builder()
                .id(1L)
                .name("John Doe")
                .address("123 Main St")
                .emailAddress("john@example.com")
                .build();
    }

    public static Cart createMockCart() {
        var user = createMockUser();

        var cartItem = CartItem.builder()
                .product(createMockProduct())
                .quantity(2)
                .build();

        var cart = Cart.builder()
                .id(1L)
                .user(user)
                .cartItems(List.of(cartItem))
                .build();

        cartItem.setCart(cart);

        var payment = Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .amount(100.0)
                .paymentMethod("Credit Card")
                .paymentStatus("INITIAL")
                .timestamp(LocalDateTime.now())
                .cart(cart)
                .build();

        cart.setPayment(payment);

        return cart;
    }

    static Product createMockProduct() {
        return Product.builder().id(1L).name("Product 1").price(10.0).build();
    }
}
