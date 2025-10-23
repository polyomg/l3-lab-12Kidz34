package com.poly.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ctrl")
public class OkController {

    // OK 1
    @PostMapping("/ok")
    public String m1(Model model) {
        model.addAttribute("msg", "Gọi m1()");
        return "ok";
    }

    // OK 2
    @GetMapping("/ok")
    public String m2(Model model) {
        model.addAttribute("msg", "Gọi m2()");
        return "ok";
    }

    // OK 3
    @PostMapping(value = "/ok", params = "x")
    public String m3(Model model) {
        model.addAttribute("msg", "Gọi m3()");
        return "ok";
    }
}
