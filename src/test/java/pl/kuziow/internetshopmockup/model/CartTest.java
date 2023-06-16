package pl.kuziow.internetshopmockup.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testEmptyCart() {
        assertThat(cart.getCartItems()).isEmpty();
    }

    @Test
    void testAddCartItem() {
        CartItem cartItem = new CartItem();
        Product product = Product.builder().id(456L).name("Another Product").description("Some description").price(15.99).quantity(1).build();

        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        cart.getCartItems().add(cartItem);

        assertThat(cart.getCartItems()).hasSize(1);
        assertThat(cart.getCartItems()).contains(cartItem);
    }

    @Test
    void testRemoveCartItem() {
        CartItem cartItem = new CartItem();
        Product product = Product.builder().id(456L).name("Another Product").description("Some description").price(15.99).quantity(1).build();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);

        cart.getCartItems().add(cartItem);

        cart.getCartItems().remove(cartItem);

        assertThat(cart.getCartItems()).isEmpty();
    }

    @Test
    void testClearCart() {
        CartItem cartItem1 = new CartItem();
        Product product = Product.builder().id(123L).name("First Product").description("Some description").price(15.99).quantity(1).build();

        cartItem1.setProduct(product);
        cartItem1.setQuantity(2);

        CartItem cartItem2 = new CartItem();
        Product product2 = Product.builder().id(456L).name("Another Product").description("Some description").price(15.99).quantity(1).build();

        cartItem2.setProduct(product2);
        cartItem2.setQuantity(1);

        cart.getCartItems().add(cartItem1);
        cart.getCartItems().add(cartItem2);

        cart.getCartItems().clear();

        assertThat(cart.getCartItems()).isEmpty();
    }
}
