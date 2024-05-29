package shop.ojtprojectdemoshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.ojtprojectdemoshop.model.dto.request.ProductRequest;
import shop.ojtprojectdemoshop.model.entity.Category;
import shop.ojtprojectdemoshop.model.entity.Product;
import shop.ojtprojectdemoshop.repository.ICategoryRepository;
import shop.ojtprojectdemoshop.repository.IProductRepository;
import shop.ojtprojectdemoshop.service.IProductService;

import java.util.Optional;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product save(ProductRequest productRequest) {
        if (productRepository.existsProductByProductName(productRequest.getName())){
            throw new RuntimeException("Product name already exists");
        }
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .productName(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .image(productRequest.getImage())
                .quantity(productRequest.getQuantity())
                .category(category)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product update(ProductRequest productRequest) {
        if (productRepository.existsProductByProductName(productRequest.getName())){
            throw new RuntimeException("Product name already exists");
        }
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .productName(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .image(productRequest.getImage())
                .quantity(productRequest.getQuantity())
                .category(category)
                .build();
        return productRepository.save(product);
    }

    @Override
    public void changeStatus(ProductRequest productRequest) {

    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }
}
