package com.poly.controller;

import com.poly.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;

@Controller
public class StaffController {

    @RequestMapping("/staff/detail")
    public String detail(Model model) {
        // Set ngày sinh cụ thể
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.DECEMBER, 23); // 23-12-2024
        Date birthday = cal.getTime();

        Staff staff = Staff.builder()
                .id("user@gmail.com")
                .fullname("nguyễn văn user")
                .photo("user.png")  // file hình đặt trong thư mục /static/photos
                .gender(true)
                .birthday(birthday)
                .salary(12345.6789)
                .level(2)
                .build();

        model.addAttribute("staff", staff);
        return "staff-detail"; // đường dẫn đến file staff-detail.html
    }
}

