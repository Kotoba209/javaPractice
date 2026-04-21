package com.example.demo1.service.impl;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public List<User> listUsers () {
        List users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setUsername("tom");
        user1.setNickname("Tom Cat");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("jerry");
        user2.setNickname("Jerry Mouse");

        users.add(user1);
        users.add(user2);
        return users;
    }

    @Override
    public User getById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("tom");
        user.setNickname("Tom Cat");
        return user;
    }
    @Override
    public User addUser(User user) {
        user.setId(100);
        return user;
    }
}
