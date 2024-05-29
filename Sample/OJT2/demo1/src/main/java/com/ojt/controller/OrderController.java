package com.ojt.controller;

import com.ojt.model.entity.OrderDetails;
import com.ojt.model.entity.Orders;
import com.ojt.model.entity.Product;
import com.ojt.service.OrderDetailService.OrderDetailService;
import com.ojt.service.OrderService.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @RequestMapping("/order")
    public String home (Model model, @Param("keyword") String keyword,
                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        if (httpSession.getAttribute("userLogin") == null) {
            return "redirect:/login";
        }
        Page<Orders> orders = orderService.getAll(pageNo);
        if (keyword != null) {
            orders = orderService.searchOrders(keyword, pageNo);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPage", orders.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("errorName", "");
        model.addAttribute("check", false);
        model.addAttribute("orders", orders);
        return "order/index";
    }
    @PostMapping("/uploadOrderFile")
    public String uploadOrderFile(@RequestParam("orderFile") MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".csv")){
            try {
                file.transferTo(new File("C:\\Module 5\\demo1\\src\\main\\resources\\uploads\\" + fileName));
                if (orderService.saveOrdersData(fileName)){
                    return "redirect:/order";
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            Page<Orders> orders = orderService.getAll(1);
            model.addAttribute("totalPage", orders.getTotalPages());
            model.addAttribute("check", true);
            model.addAttribute("errorName", "Vui lòng Import file có đuôi .csv!");
            model.addAttribute("currentPage", 1);
            model.addAttribute("orders", orders);
            return "order/index";
        }
        Page<Orders> orders = orderService.getAll(1);
        model.addAttribute("totalPage", orders.getTotalPages());
        model.addAttribute("check", true);
        model.addAttribute("errorName", "Import không thành công! Vui lòng kiểm tra lại");
        model.addAttribute("currentPage", 1);
        model.addAttribute("orders", orders);
        return "order/index";
    }

    @GetMapping("/orderDetail/{id}")
    public String orderDetail(@PathVariable("id") Long id, Model model,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Orders orders = orderService.findById(id);

        Page<OrderDetails> orderDetails = orderDetailService.getAll(orders, pageNo);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", orderDetails.getTotalPages());

        model.addAttribute("orderDetail", orderDetails);
        return "orderDetail/index";
    }
}
