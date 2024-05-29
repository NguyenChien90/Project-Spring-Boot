package com.ojt.controller;

import com.ojt.model.entity.Product;
import com.ojt.service.ProductService.ProductServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private HttpSession httpSession;
    @RequestMapping("/product")
    public String homeProduct (Model model, @Param("keyword") String keyword,
                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (httpSession.getAttribute("userLogin") == null) {
            return "redirect:/login";
        }
        Page<Product> products = productService.getAll(pageNo);
        if (keyword != null) {
            products = productService.searchProduct(keyword, pageNo);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("errorName", "");
        model.addAttribute( "check", false);
        model.addAttribute("products", products);

        return "product/index";
    }

    @PostMapping("/uploadProductFile")
    public String upload(@RequestParam("productFile")MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".csv")){
            try {
                file.transferTo(new File("C:\\Module 5\\demo1\\src\\main\\resources\\uploads\\" + fileName));
                if (productService.saveProductData(fileName)) {
                    return "redirect:/product";
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            Page<Product> products = productService.getAll(1);
            model.addAttribute("totalPage", products.getTotalPages());
            model.addAttribute("totalPage", products.getTotalPages());
            model.addAttribute("check", true);
            model.addAttribute("errorName", "Vui lòng Import file có đuôi .csv!");
            model.addAttribute("currentPage", 1);
            model.addAttribute("products", products);
        }
        Page<Product> products = productService.getAll(1);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("check", true);
        model.addAttribute("errorName", "Import không thành công! Vui lòng kiểm tra lại");
        model.addAttribute("currentPage", 1);
        model.addAttribute("products", products);
        return "product/index";
    }


}
