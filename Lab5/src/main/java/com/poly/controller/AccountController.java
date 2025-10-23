package com.poly.controller;

import com.poly.service.CookieService;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    // Hiển thị form đăng nhập
    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    // Xử lý đăng nhập
    @PostMapping("/account/login")
    public String login2() {
        // Lấy dữ liệu từ form
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        // Kiểm tra đăng nhập
        if (username.equals("poly") && password.equals("123")) {
            // Lưu username vào session
            sessionService.set("username", username);

            // Ghi nhớ tài khoản nếu chọn Remember me
            if (remember) {
                cookieService.add("user", username, 24 * 10); // 10 ngày
            } else {
                cookieService.remove("user");
            }

            // Chuyển hướng sau khi đăng nhập thành công
            return "redirect:/account/success";
        }

        // Nếu sai thông tin
        sessionService.set("error", "Sai tài khoản hoặc mật khẩu!");
        return "/account/login";
    }

    // Trang thông báo đăng nhập thành công
    @GetMapping("/account/success")
    public String success() {
        return "/account/success";
    }
}

