package pl.kuziow.internetshopmockup.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
    }

    @Test
    void testGetSetId() {
        payment.setId(123456L);

        assertThat(payment.getId()).isEqualTo(123456L);
    }

    @Test
    void testGetSetPaymentStatus() {
        payment.setPaymentStatus("Pending");

        assertThat(payment.getPaymentStatus()).isEqualTo("Pending");
    }
}
