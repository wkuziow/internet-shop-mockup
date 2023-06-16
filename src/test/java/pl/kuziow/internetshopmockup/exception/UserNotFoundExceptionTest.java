package pl.kuziow.internetshopmockup.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserNotFoundExceptionTest {

    @Test
    void constructor_MessageProvided_ShouldSetMessage() {
        var errorMessage = "User not found.";

        var exception = new UserNotFoundException(errorMessage);

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void getMessage_InitializedWithMessage_ShouldReturnMessage() {
        var errorMessage = "User not found.";
        var exception = new UserNotFoundException(errorMessage);

        var message = exception.getMessage();

        assertThat(message).isEqualTo(errorMessage);
    }
}