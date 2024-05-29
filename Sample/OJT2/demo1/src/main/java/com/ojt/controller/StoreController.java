package com.ojt.controller;

import com.ojt.model.entity.Orders;
import com.ojt.model.entity.Store;
import com.ojt.service.StoreService.StoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/store")
    public String homeStore(Model model, @Param("keyword") String keyword,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (httpSession.getAttribute("userLogin") == null) {
            return "redirect:/login";
        }
        Page<Store> stores = storeService.getAll(pageNo);
        if (keyword != null) {
            stores = storeService.searchStore(keyword, pageNo);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPage", stores.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("errorName", "");
        model.addAttribute("check", false);
        model.addAttribute("stores", stores);
        return "store/index";
    }

    @PostMapping("/uploadStoreFile")
    public String uploadStoreFile(@RequestParam("storeFile") MultipartFile file, Model model,
                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".csv")) {
            try {
                file.transferTo(new File("C:\\Module 5\\demo1\\src\\main\\resources\\uploads\\" + fileName));
                if (storeService.saveStoreData(fileName)) {
                    return "redirect:/store";
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Page<Store> stores = storeService.getAll(pageNo);
            model.addAttribute("totalPage", stores.getTotalPages());
            model.addAttribute("check", true);
            model.addAttribute("errorName", "Vui lòng Import file có đuôi .csv!");
            model.addAttribute("currentPage", 1);
            model.addAttribute("orders", stores);
            return "store/index";
        }
        Page<Store> stores = storeService.getAll(pageNo);
        model.addAttribute("totalPage", stores.getTotalPages());
        model.addAttribute("check", true);
        model.addAttribute("errorName", "Import không thành công! Vui lòng kiểm tra lại");
        model.addAttribute("currentPage", 1);
        model.addAttribute("orders", stores);

        return "store/index";
    }
}
