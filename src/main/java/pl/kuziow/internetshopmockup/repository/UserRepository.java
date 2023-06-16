package pl.kuziow.internetshopmockup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuziow.internetshopmockup.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
