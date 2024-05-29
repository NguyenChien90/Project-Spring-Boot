package shop.ojtprojectdemoshop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.ojtprojectdemoshop.model.dto.request.CategoryRequest;
import shop.ojtprojectdemoshop.model.entity.Category;
import shop.ojtprojectdemoshop.repository.ICategoryRepository;
import shop.ojtprojectdemoshop.service.ICategoryService;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category save(CategoryRequest categoryRequest) {
        Category categoryNew = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .status(true)
                .build();
        return categoryRepository.save(categoryNew);
    }

    @Override
    public Category update(CategoryRequest categoryRequest, Long id) {
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        categoryUpdate.setCategoryName(categoryRequest.getCategoryName());
        categoryUpdate.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void changeStatus(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.setStatus(!category.isStatus());
        categoryRepository.save(category);
    }


}
