package pl.kuziow.internetshopmockup.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductNotFoundExceptionTest {

    @Test
    void constructor_MessageProvided_ShouldSetMessage() {
        var errorMessage = "Product not found.";

        var exception = new ProductNotFoundException(errorMessage);

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void getMessage_InitializedWithMessage_ShouldReturnMessage() {
        var errorMessage = "Product not found.";
        var exception = new ProductNotFoundException(errorMessage);

        String message = exception.getMessage();

        assertThat(message).isEqualTo(errorMessage);
    }
}