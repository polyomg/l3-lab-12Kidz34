package com.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    HttpServletRequest request;

    @GetMapping("/login/form")
    public String form() {
        return "form";
    }

    @PostMapping("/login/check")
    public String login(Model model, RedirectAttributes redirectAttributes) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("poly".equals(username) && "123".equals(password)) {
            model.addAttribute("message", "Đăng nhập thành công!");
            return "login";
        } else {
            redirectAttributes.addFlashAttribute("message", "Đăng nhập thất bại! Sai username hoặc password.");
            return "redirect:/login/form";
        }
    }

}
