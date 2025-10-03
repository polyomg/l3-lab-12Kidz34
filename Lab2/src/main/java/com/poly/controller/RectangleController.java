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
            if (width <= 0 || height <= 0 || height >= width) {
                double area = width * height;
                double perimeter = 2 * (width + height);

                String chuVi = "(" + width + " + " + height + ") x 2 = " + perimeter;
                String dienTich = width + " x " + height + " = " + area;

                model.addAttribute("chuVi", chuVi);
                model.addAttribute("dienTich", dienTich);
                model.addAttribute("width", width);
                model.addAttribute("height", height);
                model.addAttribute("area", area);
                model.addAttribute("perimeter", perimeter);
            } else {
                double width2 = height;
                double height2 = width;

                double area = width2 * height2;
                double perimeter = 2 * (width2 + height2);

                String chuVi = "(" + width2 + " + " + height2 + ") x 2 = " + perimeter;
                String dienTich = width2 + " x " + height2 + " = " + area;

                model.addAttribute("chuVi", chuVi);
                model.addAttribute("dienTich", dienTich);
                model.addAttribute("width", width2);
                model.addAttribute("height", height2);
                model.addAttribute("area", area);
                model.addAttribute("perimeter", perimeter);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Vui lòng nhập đúng số hợp lệ!");
        }

        return "rectangle-result";
    }
}
