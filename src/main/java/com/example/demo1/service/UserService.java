package com.example.demo1.service;
import com.example.demo1.pojo.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    User getById(Integer id);

    User addUser(User user);
}


