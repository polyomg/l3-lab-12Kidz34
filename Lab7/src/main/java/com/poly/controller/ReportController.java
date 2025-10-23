package com.poly.controller;

import com.poly.dao.ProductDAO;
import com.poly.dto.InventoryReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ProductDAO dao;

    @RequestMapping("/inventory-by-category")
    public String inventory(Model model) {
        List<InventoryReport> items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        return "report/inventory-by-category";
    }
}

