package com.poly.controller;

import java.util.*;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO dao;

    @Autowired
    SessionService session;

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {

        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);

        List<Product> items = dao.findByPrice(minPrice, maxPrice);
        model.addAttribute("items", items);

        return "product/search";
    }

    @GetMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {

        // Lấy keyword hiện tại hoặc keyword cũ từ session
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);

        // Nếu người dùng nhập số trang < 0 thì reset về 0
        int currentPage = p.orElse(0);
        if (currentPage < 0) currentPage = 0;

        Pageable pageable = PageRequest.of(currentPage, 5);
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);
        return "product/search-and-page";
    }

    //bai4

    @GetMapping("/searchDSL")
    public String searchDSL(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {

        double minPrice = min.orElse(0.0);
        double maxPrice = max.orElse(Double.MAX_VALUE);

        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);

        return "product/searchDSL";
    }

    @RequestMapping("/search-and-page-DSL")
    public String searchAndPageDSL(
            @RequestParam(name = "keywords", defaultValue = "") String keywords,
            @RequestParam(name = "p", defaultValue = "0") int page,
            Model model) {
        
        if (page < 0) page = 0; // ✅ tránh lỗi âm
        PageRequest pageable = PageRequest.of(page, 5); // mỗi trang 5 sản phẩm
        Page<Product> result = dao.findAllByNameLike("%" + keywords + "%", pageable);

        model.addAttribute("page", result);
        model.addAttribute("keywords", keywords);
        return "product/search-and-page-DSL";
    }

}
