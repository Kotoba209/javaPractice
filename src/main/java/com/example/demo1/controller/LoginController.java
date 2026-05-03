package com.example.demo1.controller;

import com.example.demo1.exception.BusinessException;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.pojo.LoginInfo;
import com.example.demo1.pojo.Result;
import com.example.demo1.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserMapper userMapper;

    public LoginController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public Result<Void> login(@RequestBody @Validated LoginInfo loginInfo, HttpSession session) {
        User user = userMapper.findUserByName(loginInfo.getUsername());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        String password = userMapper.getPasswordByUsername(loginInfo.getUsername());
        if (!password.equals(loginInfo.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        session.setAttribute("loginUser", loginInfo.getUsername());
        return Result.success();
    }

    @PostMapping("/logout")
    public Result<Void> logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return Result.success();
    }
}
