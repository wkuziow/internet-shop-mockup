package pl.kuziow.internetshopmockup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuziow.internetshopmockup.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
