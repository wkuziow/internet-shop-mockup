package pl.kuziow.internetshopmockup.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kuziow.internetshopmockup.Fixture;
import pl.kuziow.internetshopmockup.exception.ProductNotFoundException;
import pl.kuziow.internetshopmockup.exception.UserNotFoundException;
import pl.kuziow.internetshopmockup.model.*;
import pl.kuziow.internetshopmockup.repository.CartRepository;
import pl.kuziow.internetshopmockup.repository.ProductRepository;
import pl.kuziow.internetshopmockup.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaymentService paymentService;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService(cartRepository, productRepository, userRepository, paymentService);
    }

    @Test
    void processCartRequest_ValidCartRequest_ShouldSaveCartAndCallPaymentService() {
        var cartRequest = Fixture.createValidCartRequest();

        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Product product = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        when(cartRepository.save(any(Cart.class))).thenReturn(new Cart());

        // Act
        cartService.processCartRequest(cartRequest);

        // Assert
        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(paymentService, times(1)).paymentOrchestrator(any(Cart.class), anyString());
    }

    @Test
    void processCartRequest_UserNotFound_ShouldThrowUserNotFoundException() {
        var cartRequest = Fixture.createValidCartRequest();

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> cartService.processCartRequest(cartRequest));
        verify(cartRepository, never()).save(any(Cart.class));
        verify(paymentService, never()).paymentOrchestrator(any(Cart.class), anyString());
    }

    @Test
    void processCartRequest_ProductNotFound_ShouldThrowProductNotFoundException() {
        var cartRequest = Fixture.createValidCartRequest();

        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> cartService.processCartRequest(cartRequest));
        verify(cartRepository, never()).save(any(Cart.class));
        verify(paymentService, never()).paymentOrchestrator(any(Cart.class), anyString());
    }

    private CartRequest createValidCartRequest() {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setCustomerId("1");

        Map<String, Integer> products = new HashMap<>();
        products.put("1", 2);
        products.put("2", 1);
        cartRequest.setProducts(products);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentMethod("Credit Card");
        cartRequest.setPayment(paymentRequest);

        return cartRequest;
    }
}

