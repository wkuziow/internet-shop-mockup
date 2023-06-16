package pl.kuziow.internetshopmockup.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentRequestTest {

    @Test
    void setPaymentMethod_ValidPaymentMethod_ShouldSetPaymentMethod() {
        String paymentMethod = "Credit Card";
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setPaymentMethod(paymentMethod);

        assertThat(paymentRequest.getPaymentMethod()).isEqualTo(paymentMethod);
    }

}