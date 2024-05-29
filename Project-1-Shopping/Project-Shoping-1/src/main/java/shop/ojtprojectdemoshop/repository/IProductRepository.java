package shop.ojtprojectdemoshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.ojtprojectdemoshop.model.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductByProductName(String name);
}
