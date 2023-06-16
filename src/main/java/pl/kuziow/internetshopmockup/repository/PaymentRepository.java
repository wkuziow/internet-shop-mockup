package pl.kuziow.internetshopmockup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuziow.internetshopmockup.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
