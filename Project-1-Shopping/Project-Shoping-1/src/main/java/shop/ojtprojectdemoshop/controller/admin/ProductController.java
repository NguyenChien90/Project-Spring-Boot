package shop.ojtprojectdemoshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.ojtprojectdemoshop.model.dto.request.ProductRequest;
import shop.ojtprojectdemoshop.model.dto.response.ResponseData;
import shop.ojtprojectdemoshop.model.entity.Product;
import shop.ojtprojectdemoshop.service.IProductService;

@RestController
@RequestMapping("/api/v2/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping()
    public ResponseData<?> getProducts(Pageable pageable) {
        Page<Product> listPro = productService.findAll(pageable);
        return new ResponseData<>(HttpStatus.OK.value(),"List Product", listPro);
    }

    @PostMapping()
    public ResponseData<?> addProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.save(productRequest);
        return new ResponseData<>(HttpStatus.OK.value(),"Product Added", product);
    }

    @PutMapping("/{id}")
    public ResponseData<?> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(()->new RuntimeException("Product Not Found"));
        productService.update(productRequest);
        return new ResponseData<>(HttpStatus.OK.value(),"Product Updated", product);
    }
}
