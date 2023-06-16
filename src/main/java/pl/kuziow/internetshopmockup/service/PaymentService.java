package pl.kuziow.internetshopmockup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kuziow.internetshopmockup.model.Cart;
import pl.kuziow.internetshopmockup.model.Payment;
import pl.kuziow.internetshopmockup.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.System.out;

@Service
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    public PaymentService(PaymentRepository paymentRepository, EmailService emailService) {
        this.paymentRepository = paymentRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void paymentOrchestrator(Cart cart, String paymentMethod) {
        var savedPayment = initializePayment(cart, paymentMethod);

        mockSendPaymentToPaymentSystem(savedPayment);
        emailService.mockSendNotification(savedPayment.getCart().getUser().getEmailAddress(),
                "Payment Status", generateEmailMessage(savedPayment));
        mockReceivePaymentStatus(savedPayment);
        emailService.mockSendNotification(savedPayment.getCart().getUser().getEmailAddress(), "Payment Status",
                generateEmailMessage(savedPayment));
    }

    private Payment initializePayment(Cart cart, String paymentMethod) {
        var payment = Payment.builder()
                .paymentId(generateUniquePaymentId())
                .amount(calculatePaymentAmount(cart))
                .paymentMethod(paymentMethod)
                .paymentStatus("INITIAL")
                .timestamp(LocalDateTime.now())
                .cart(cart)
                .build();

        log.info("Created payment: {}", payment);

        var savedPayment = paymentRepository.save(payment);

        log.info("Saved payment: {}", savedPayment);
        return savedPayment;
    }

    private static String generateEmailMessage(Payment payment) {
        return switch (payment.getPaymentStatus()) {
            case "SENT" -> "Your payment is proceeded";
            case "SUCCESS" -> "Your payment is successful and your order will be shipped";
            case "PENDING" -> "Your payment is pending";
            case "FAILURE" -> "Your payment failed. Please contact your bank. Your order will not be shipped";
            default -> "";
        };
    }

    private void mockReceivePaymentStatus(Payment payment) {
        log.info("About to receive payment status for payment: [{}]", payment);
        out.println("Receiving payment status (Mock)");
        out.println("Payment ID: " + payment.getPaymentId());

        var paymentStatus = generatePaymentStatus();
        payment.setPaymentStatus(paymentStatus);
        out.println("New payment status " + paymentStatus);
        log.info("New payment status was received: {}", paymentStatus);
        paymentRepository.save(payment);
    }

    private static String generatePaymentStatus() {
        var successRatio = Math.random();
        if (successRatio < 0.8D) {
            return "SUCCESS";
        } else if (successRatio < 0.9) {
            return "PENDING";
        } else {
            return "FAILURE";
        }
    }

    private void mockSendPaymentToPaymentSystem(Payment payment) {
        log.info("Starting sending payment to payment system: {}", payment);
        out.println("Sending payment to payment system (Mock)");
        out.println("Payment ID: " + payment.getPaymentId());
        out.println("Total Amount: " + payment.getAmount());
        payment.setPaymentStatus("SENT");
        log.info("Payment {} was sent, status was updated.", payment);
        paymentRepository.save(payment);
    }

    private static double calculatePaymentAmount(Cart cart) {
        return cart.getCartItems().stream().mapToDouble(cartItem ->
                cartItem.getProduct().getPrice() * cartItem.getQuantity()).sum();
    }

    private static String generateUniquePaymentId() {
        return UUID.randomUUID().toString();
    }
}
