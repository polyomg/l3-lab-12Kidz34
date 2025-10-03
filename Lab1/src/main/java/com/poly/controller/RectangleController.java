package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RectangleController {

    @GetMapping("/rectangle/form")
    public String form() {
        return "rectangle-form";
    }

    @PostMapping("/rectangle/calculate")
    public String calculate(
            @RequestParam("width") String widthStr,
            @RequestParam("height") String heightStr,
            Model model) {

        try {
            double width = Double.parseDouble(widthStr);
            double height = Double.parseDouble(heightStr);

            double area = width * height;
            double perimeter = 2 * (width + height);

            model.addAttribute("width", width);
            model.addAttribute("height", height);
            model.addAttribute("area", area);
            model.addAttribute("perimeter", perimeter);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Vui lòng nhập đúng số hợp lệ!");
        }

        return "rectangle-result";
    }
}
