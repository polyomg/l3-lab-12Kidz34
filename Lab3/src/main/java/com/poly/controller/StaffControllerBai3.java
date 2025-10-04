package com.poly.controller;

import com.poly.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StaffControllerBai3 {

    @RequestMapping("/staff/list/detail")
    public String list(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("user1@gmail.com").fullname("Nguyễn Văn User1").level(0).build(),
                Staff.builder().id("user2@gmail.com").fullname("Nguyễn Văn User2").level(1).build(),
                Staff.builder().id("user3@gmail.com").fullname("Nguyễn Văn User3").level(2).build(),
                Staff.builder().id("user4@gmail.com").fullname("Nguyễn Văn User4").level(2).build(),
                Staff.builder().id("user5@gmail.com").fullname("Nguyễn Văn User5").level(1).build(),
                Staff.builder().id("user6@gmail.com").fullname("Nguyễn Văn User6").level(0).build()
        );

        model.addAttribute("list", list);
        return "list-status"; // view list-status.html
    }
}

