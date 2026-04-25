package com.example.demo1.controller;

//import com.example.demo1.mapper.UserMapper;
import com.example.demo1.pojo.PageResult;
import com.example.demo1.pojo.Result;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import com.example.demo1.validation.AddGroup;
import com.example.demo1.validation.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result<List< User>> listUsers() {
        List<User> users = userService.listUsers();
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable @Validated Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PostMapping
    public Result<User> addUser(@RequestBody @Validated(AddGroup.class) User user) {
        User saveUser = userService.addUser(user);
        return Result.success(saveUser);
    }
    @PutMapping
    public Result<User> updateUser(@RequestBody @Validated(UpdateGroup.class) User user) {
        System.out.println("传入id:" + user.getId());
        User updateUser = userService.getById(user.getId());
        updateUser.setUsername(user.getUsername());
        updateUser.setNickname(user.getNickname());
        userService.updateUser(updateUser);
        return Result.success(updateUser);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUser(@PathVariable @Validated Integer id) {
        User deleteUser = userService.getById(id);
        userService.deleteUser(id);
        return Result.success(deleteUser);
    }
    @GetMapping("/testException")
    public Result<User> testException() {
        int i = 1 / 0;
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult<User>> page(Integer pageNum, Integer pageSize, String username) {
        PageResult<User> pageResult = userService.page(pageNum, pageSize, username);
        return Result.success(pageResult);
    }
}
