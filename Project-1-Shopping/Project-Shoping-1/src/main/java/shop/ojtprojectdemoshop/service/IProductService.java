package shop.ojtprojectdemoshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.ojtprojectdemoshop.model.dto.request.ProductRequest;
import shop.ojtprojectdemoshop.model.entity.Product;

import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    Product save(ProductRequest product);
    Product update(ProductRequest product);
    void changeStatus(ProductRequest product);
    Optional<Product> findById(Long id);
}
