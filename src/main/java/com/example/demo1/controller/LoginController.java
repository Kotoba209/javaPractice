package com.example.demo1.controller;

import com.example.demo1.pojo.LoginInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginInfo loginInfo, HttpSession session) {
        if ("admin".equals(loginInfo.getUsername()) && "123456".equals(loginInfo.getPassword())) {
            session.setAttribute("loginUser", loginInfo.getUsername());
            return "登录成功";
        }
        return "用户名或密码错误";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "注销成功";
    }
}
