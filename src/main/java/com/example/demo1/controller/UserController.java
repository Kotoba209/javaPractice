package com.example.demo1.controller;

//import com.example.demo1.mapper.UserMapper;

import com.example.demo1.pojo.PageResult;
import com.example.demo1.pojo.Result;
import com.example.demo1.pojo.User;
import com.example.demo1.pojo.UserAddDTO;
import com.example.demo1.service.UserService;
import com.example.demo1.validation.AddGroup;
import com.example.demo1.validation.UpdateGroup;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
//    @Autowired
//    private UserMapper userMapper;

    @GetMapping
    public Result<List<User>> listUsers() {
        List<User> users = userService.listUsers();
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable @Validated Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PostMapping
    public Result<User> addUser(@RequestBody @Validated(AddGroup.class) UserAddDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setNickname(dto.getNickname());
        User saveUser = userService.addUser(user);
        return Result.success(saveUser);
    }

    @PutMapping
    public Result<User> updateUser(@RequestBody @Validated(UpdateGroup.class) User user) {
        System.out.println("传入id:" + user.getId());
        User updateUser = userService.updateUser(user);
        return Result.success(updateUser);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@PathVariable @Validated Integer id) {
        userService.deleteUser(id);
        return Result.success();
    }

    @GetMapping("/testException")
    public Result<User> testException() {
        int i = 1 / 0;
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult<User>> page(
            @RequestParam @Min(value = 1, message = "页码不能小于1") Integer pageNum,
            @RequestParam @Min(value = 1, message = "页大小不能小于1") @Max(value = 20, message = "页大小不能大于20") Integer pageSize,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false, defaultValue = "asc") String sort) {
        System.out.println("nickname = " + nickname);
        
        PageResult<User> pageResult = userService.page(pageNum, pageSize, username, nickname, sort);
        return Result.success(pageResult);
    }

    @GetMapping("/me")
    public Result<String> currentUser(HttpSession session) {
        String currentUser = (String) session.getAttribute("loginUser");
        return Result.success(currentUser);
    }
}
