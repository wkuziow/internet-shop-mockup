package pl.kuziow.internetshopmockup.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.kuziow.internetshopmockup.model.CartRequest;
import pl.kuziow.internetshopmockup.service.CartService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Mock
    private CartService cartService;

    private CartController cartController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cartController = new CartController(cartService);
    }

    @Test
    void testReceiveCart_Success() {
        CartRequest cartRequest = new CartRequest();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Cart request processed successfully");

        doNothing().when(cartService).processCartRequest(cartRequest);

        ResponseEntity<String> response = cartController.receiveCart(cartRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isEqualTo(expectedResponse);

        verify(cartService, times(1)).processCartRequest(cartRequest);
    }

    @Test
    void testReceiveCart_Exception() {
        CartRequest cartRequest = new CartRequest();
        String errorMessage = "Error processing cart request";
        ResponseEntity<String> expectedResponse =
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);

        doThrow(new RuntimeException("Error processing cart request")).when(cartService).processCartRequest(cartRequest);

        ResponseEntity<String> response = cartController.receiveCart(cartRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response).isEqualTo(expectedResponse);

        verify(cartService, times(1)).processCartRequest(cartRequest);
    }
}