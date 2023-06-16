package pl.kuziow.internetshopmockup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuziow.internetshopmockup.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
