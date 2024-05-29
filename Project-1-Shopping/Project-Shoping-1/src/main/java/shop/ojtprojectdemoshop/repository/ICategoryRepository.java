package shop.ojtprojectdemoshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.ojtprojectdemoshop.model.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
