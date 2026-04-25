package com.example.demo1.service;
import com.example.demo1.pojo.PageResult;
import com.example.demo1.pojo.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    User getById(Integer id);

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(Integer id);

    PageResult< User> page(Integer pageNum, Integer pageSize, String username, String nickname, String sort);

    User getByUsername(String username);
}


