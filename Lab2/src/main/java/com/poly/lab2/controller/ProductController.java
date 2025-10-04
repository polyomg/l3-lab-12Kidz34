package com.poly.controller;

import com.poly.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    // Hàm sinh ra 20 sản phẩm giả lập
    public List<Product> getItems() {
        List<Product> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(new Product("Sản phẩm " + i, i * 1000.0));
        }
        return list;
    }

    // Hiển thị form + danh sách có phân trang
    @GetMapping("/product/form")
    public String form(Model model,
                       @RequestParam(defaultValue = "1") int page) {

        int pageSize = 5; // mỗi trang 5 sản phẩm
        List<Product> products = getItems();

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, products.size());

        List<Product> pageList = products.subList(fromIndex, toIndex);

        // Gửi dữ liệu sang view
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(5000.0);

        model.addAttribute("p", p);
        model.addAttribute("items", pageList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) products.size() / pageSize));

        return "Lab02Bai03"; // trỏ tới file HTML (Lab02Bai03.html)
    }

    // Lưu sản phẩm nhập từ form (giữ nguyên nhưng thêm phân trang)
    @PostMapping("/product/save")
    public String save(@ModelAttribute("p") Product p, Model model,
                       @RequestParam(defaultValue = "1") int page) {

        int pageSize = 5;
        List<Product> products = getItems();

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, products.size());

        List<Product> pageList = products.subList(fromIndex, toIndex);

        model.addAttribute("p", p);
        model.addAttribute("items", pageList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) products.size() / pageSize));

        return "Lab02Bai03";
    }
}
