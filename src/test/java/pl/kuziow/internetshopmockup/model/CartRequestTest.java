package pl.kuziow.internetshopmockup.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class CartRequestTest {
    @Test
    void setCustomerId_ValidCustomerId_ShouldSetCustomerId() {
        String customerId = "123";
        CartRequest cartRequest = new CartRequest();

        cartRequest.setCustomerId(customerId);

        assertThat(cartRequest.getCustomerId()).isEqualTo(customerId);
    }

    @Test
    void setProducts_ValidProducts_ShouldSetProducts() {
        Map<String, Integer> products = new HashMap<>();
        products.put("product1", 1);
        products.put("product2", 2);
        CartRequest cartRequest = new CartRequest();

        cartRequest.setProducts(products);

        assertThat(cartRequest.getProducts()).isEqualTo(products);
    }

    @Test
    void setPayment_ValidPayment_ShouldSetPayment() {
        PaymentRequest payment = new PaymentRequest();
        CartRequest cartRequest = new CartRequest();

        cartRequest.setPayment(payment);

        assertThat(cartRequest.getPayment()).isEqualTo(payment);
    }
}