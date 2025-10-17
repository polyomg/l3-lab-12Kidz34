package com.poly.controller;

import com.poly.entity.User;
import com.poly.service.CookieService;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class RegisterController {

    @Autowired
    ParamService paramService;

    @Autowired
    CookieService cookieService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/account/register")
    public String showForm(Model model) {
        // Nếu người dùng có cookie username => tự động điền vào form
        String username = cookieService.getValue("username");
        model.addAttribute("username", username);
        return "/account/register";
    }

    @PostMapping("/account/register")
    public String register(Model model,
                           @RequestParam("photo_file") MultipartFile file) {

        // Lấy thông tin từ form
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        String fullname = paramService.getString("fullname", "");
        String email = paramService.getString("email", "");

        // Lưu file ảnh
        File savedFile = paramService.save(file, "/uploads/");
        String photoName = (savedFile != null) ? savedFile.getName() : "default.png";

        // Tạo đối tượng User
        User user = new User(username, password, fullname, email, photoName);

        // Lưu user vào session
        sessionService.set("currentUser", user);

        // Ghi nhớ username vào cookie trong 24 giờ
        cookieService.add("username", username, 24);

        // Truyền ra view
        model.addAttribute("user", user);
        model.addAttribute("message", "Đăng ký thành công và đã lưu phiên làm việc!");

        return "/account/register";
    }
}
