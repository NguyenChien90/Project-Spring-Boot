package shop.ojtprojectdemoshop.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.ojtprojectdemoshop.model.dto.request.CategoryRequest;
import shop.ojtprojectdemoshop.model.entity.Category;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);
    Category save(CategoryRequest category);
    Category update(CategoryRequest category, Long id);
    Optional<Category> findById(Long id);
    void changeStatus(Long id);
}
