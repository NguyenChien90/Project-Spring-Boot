package shop.ojtprojectdemoshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ojtprojectdemoshop.model.entity.Role;
import shop.ojtprojectdemoshop.model.entity.RoleName;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
