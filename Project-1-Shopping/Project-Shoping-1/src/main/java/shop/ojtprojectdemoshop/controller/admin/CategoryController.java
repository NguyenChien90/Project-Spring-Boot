package shop.ojtprojectdemoshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.ojtprojectdemoshop.model.dto.request.CategoryRequest;
import shop.ojtprojectdemoshop.model.dto.response.ResponseData;
import shop.ojtprojectdemoshop.model.entity.Category;
import shop.ojtprojectdemoshop.service.ICategoryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    public ResponseData<Page<Category>> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        return new ResponseData<>(HttpStatus.OK.value(), "List Categories",categories);
    }

    @GetMapping("/{id}")
    public ResponseData<Category> getCategoryById(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
        return new ResponseData<>(HttpStatus.OK.value(), "",category);
    }

    @PostMapping()
    public ResponseData<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.save(categoryRequest);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Create category success",category);
    }

    @PutMapping("/{id}")
    public ResponseData<Category> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) {
        Category updatedCategory = categoryService.update(categoryRequest, id);
        return new ResponseData<>(HttpStatus.OK.value(), "Update category success", updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseData<Category> deleteCategory(@PathVariable("id") Long id) {
        if (categoryService.findById(id).get().isStatus()){
            categoryService.changeStatus(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Delete category success",categoryService.findById(id).get());
        }
        return new ResponseData<>(HttpStatus.NOT_FOUND.value(), "Delete category not found or block");
    }
}
