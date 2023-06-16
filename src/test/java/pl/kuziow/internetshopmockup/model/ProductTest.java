package pl.kuziow.internetshopmockup.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(10.99);
        product.setQuantity(5);
    }

    @Test
    void testGettersAndSetters() {
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Test Product");
        assertThat(product.getDescription()).isEqualTo("This is a test product");
        assertThat(product.getPrice()).isEqualTo(10.99);
        assertThat(product.getQuantity()).isEqualTo(5);

        product.setId(2L);
        product.setName("Updated Product");
        product.setDescription("This is an updated product");
        product.setPrice(19.99);
        product.setQuantity(10);

        assertThat(product.getId()).isEqualTo(2L);
        assertThat(product.getName()).isEqualTo("Updated Product");
        assertThat(product.getDescription()).isEqualTo("This is an updated product");
        assertThat(product.getPrice()).isEqualTo(19.99);
        assertThat(product.getQuantity()).isEqualTo(10);
    }

    @Test
    void testToString() {
        String expectedString = "Product(id=1, name=Test Product, description=This is a test product, " +
                "price=10.99, quantity=5)";
        assertThat(product).hasToString(expectedString);
    }
}