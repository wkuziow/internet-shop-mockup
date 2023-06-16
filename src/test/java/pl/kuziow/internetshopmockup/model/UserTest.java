package pl.kuziow.internetshopmockup.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void setId_ValidId_ShouldSetId() {
        Long id = 1L;
        User user = new User();

        user.setId(id);

        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void setName_ValidName_ShouldSetName() {
        String name = "John Doe";
        User user = new User();

        user.setName(name);

        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    void setAddress_ValidAddress_ShouldSetAddress() {
        String address = "123 Main St";
        User user = new User();

        user.setAddress(address);

        assertThat(user.getAddress()).isEqualTo(address);
    }

    @Test
    void setEmailAddress_ValidEmailAddress_ShouldSetEmailAddress() {
        String emailAddress = "john.doe@example.com";
        User user = new User();

        user.setEmailAddress(emailAddress);

        assertThat(user.getEmailAddress()).isEqualTo(emailAddress);
    }
}