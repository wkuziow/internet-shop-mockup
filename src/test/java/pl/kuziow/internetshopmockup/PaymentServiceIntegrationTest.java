package pl.kuziow.internetshopmockup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.kuziow.internetshopmockup.model.Payment;
import pl.kuziow.internetshopmockup.repository.PaymentRepository;
import pl.kuziow.internetshopmockup.service.EmailService;
import pl.kuziow.internetshopmockup.service.PaymentService;

import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentServiceIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private EmailService emailService;

    @Test
    void testPaymentOrchestrator_ValidCartAndPaymentMethod_CallsDependenciesAndUpdatesPayment() {
        var cart = Fixture.createMockCart();

        var paymentMethod = "Credit Card";

        var savedPayment = Fixture.createMockPayment();

        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        paymentService.paymentOrchestrator(cart, paymentMethod);

        verify(paymentRepository, times(3)).save(any(Payment.class));
        verify(emailService, times(2)).mockSendNotification(anyString(), anyString(), anyString());
    }
}
