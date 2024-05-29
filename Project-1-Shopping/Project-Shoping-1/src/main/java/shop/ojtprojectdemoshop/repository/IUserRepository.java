package shop.ojtprojectdemoshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ojtprojectdemoshop.model.entity.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
