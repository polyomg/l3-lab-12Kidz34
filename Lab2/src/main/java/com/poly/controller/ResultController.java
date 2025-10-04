package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResultController {

    @RequestMapping("/a")
    public String m1() {
        return "a"; // view a.html
    }

    @RequestMapping("/b")
    public String m2(Model model) {
        // Truyền message bằng Model
        model.addAttribute("message", "I come from b");
        return "a"; // Dùng lại view a.html
    }

    @RequestMapping("/c")
    public String m3(RedirectAttributes params) {
        // Truyền message bằng RedirectAttributes
        params.addFlashAttribute("message", "I come from c");
        return "redirect:/d"; // redirect sang /d
    }

    @RequestMapping("/d")
    public String m4() {
        return "a"; // Dùng lại view a.html
    }
}
