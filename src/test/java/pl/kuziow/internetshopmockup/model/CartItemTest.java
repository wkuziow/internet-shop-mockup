package pl.kuziow.internetshopmockup.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartItemTest {

    private CartItem cartItem;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(10.99);
        product.setQuantity(5);

        cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(2);
    }

    @Test
    void testGettersAndSetters() {
        assertThat(cartItem.getProduct()).isEqualTo(product);
        assertThat(cartItem.getQuantity()).isEqualTo(2);

        // Modify the values
        Product newProduct = new Product();
        newProduct.setId(2L);
        newProduct.setName("New Product");
        newProduct.setDescription("This is a new product");
        newProduct.setPrice(19.99);
        newProduct.setQuantity(10);

        cartItem.setProduct(newProduct);
        cartItem.setQuantity(4);

        assertThat(cartItem.getProduct()).isEqualTo(newProduct);
        assertThat(cartItem.getQuantity()).isEqualTo(4);
    }

    @Test
    void testToString() {
        String expectedString = "CartItem(id=null, cart=null, product=Product(id=1, name=Test Product, " +
                "description=This is a test product, price=10.99, quantity=5), quantity=2)";
        assertThat(cartItem).hasToString(expectedString);
    }
}