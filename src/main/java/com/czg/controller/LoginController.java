package com.czg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session) {
        if ("a".equals(username) && "123".equals(password)) {
            // 设置session
            session.setAttribute("loginUser", username);
            // 重定向
            return "redirect:/";
        }
        return "";
    }
}
