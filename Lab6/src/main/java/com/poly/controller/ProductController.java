package com.poly.controller;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductDAO dao;

    // 🟡 1. SORT - Sắp xếp theo trường chỉ định (mặc định: price giảm dần)
    @RequestMapping("/product/sort")
    public String sort(Model model,
                       @RequestParam("field") Optional<String> field) {

        // Nếu không có field thì mặc định sắp theo price giảm dần
        String sortField = field.orElse("price");
        Sort sort = Sort.by(Direction.DESC, sortField);

        List<Product> items = dao.findAll(sort);

        model.addAttribute("items", items);
        model.addAttribute("field", sortField.toUpperCase());

        return "product/sort";
    }

    // 🟢 2. PAGINATION - Phân trang sản phẩm (5 sp/trang)
    @RequestMapping("/product/page")
    public String paginate(Model model,
                           @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);

        model.addAttribute("page", page);
        return "product/page";
    }

    @RequestMapping("/product/page-sort")
    public String paginateAndSort(Model model,
                                  @RequestParam("p") Optional<Integer> p,
                                  @RequestParam("field") Optional<String> field) {

        String sortField = field.orElse("price");
        Sort sort = Sort.by(Direction.DESC, sortField);
        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);

        Page<Product> page = dao.findAll(pageable);

        model.addAttribute("page", page);
        model.addAttribute("field", sortField.toUpperCase());
        return "product/page-sort";
    }
}
