package pl.kuziow.internetshopmockup.service;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import pl.kuziow.internetshopmockup.model.Cart;
import pl.kuziow.internetshopmockup.model.Payment;
import pl.kuziow.internetshopmockup.repository.PaymentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static pl.kuziow.internetshopmockup.Fixture.createMockCart;
import static pl.kuziow.internetshopmockup.Fixture.createMockPayment;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private EmailService emailService;

    private PaymentService paymentService;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;

    public PaymentServiceTest() {
        MockitoAnnotations.openMocks(this);
        paymentService = new PaymentService(paymentRepository, emailService);
    }

    @Test
    void paymentOrchestrator_ValidCartAndPaymentMethod_ShouldInitializePaymentAndSendNotifications() {
        // Arrange
        Cart cart = createMockCart();
        String paymentMethod = "Credit Card";
        String expectedPaymentStatus = "SUCCESS";
        String expectedEmailMessage = "Your payment is successful and your order will be shipped";
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(createMockPayment());

        // Mocking the sendNotification method of EmailService
        doNothing().when(emailService).mockSendNotification(anyString(), anyString(), anyString());

        // Act
        paymentService.paymentOrchestrator(cart, paymentMethod);

        // Assert
        verify(paymentRepository, times(3)).save(paymentCaptor.capture());

        Payment savedPayment = paymentCaptor.getValue();
        assertThat(savedPayment.getPaymentStatus()).isEqualTo(expectedPaymentStatus);
        assertThat(savedPayment.getCart().getId()).isEqualTo(cart.getId());
    }



}


