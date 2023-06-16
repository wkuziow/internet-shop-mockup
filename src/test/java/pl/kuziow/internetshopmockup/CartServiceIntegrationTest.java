package pl.kuziow.internetshopmockup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.kuziow.internetshopmockup.model.Cart;
import pl.kuziow.internetshopmockup.repository.CartRepository;
import pl.kuziow.internetshopmockup.repository.ProductRepository;
import pl.kuziow.internetshopmockup.repository.UserRepository;
import pl.kuziow.internetshopmockup.service.CartService;
import pl.kuziow.internetshopmockup.service.PaymentService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static pl.kuziow.internetshopmockup.Fixture.*;

@SpringBootTest
class CartServiceIntegrationTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testProcessCartRequest_ValidCartRequest_CallsDependenciesAndSavesCart() {
        var cartRequest = createValidCartRequest();

        var user = createMockUser();

        var product = createMockProduct();

        var cart = createMockCart();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.processCartRequest(cartRequest);

        verify(userRepository, times(1)).findById(anyLong());
        verify(productRepository, times(cartRequest.getProducts().size())).findById(anyLong());
        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(paymentService, times(1)).paymentOrchestrator(eq(cart), anyString());
    }
}
