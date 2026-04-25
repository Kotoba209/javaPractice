package com.example.demo1.service.impl;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.pojo.PageResult;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> listUsers() {
        return userMapper.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User addUser(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        userMapper.updateById(user);
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public PageResult<User> page(Integer pageNum, Integer pageSize, String username) {
        Integer offset = (pageNum - 1) * pageSize;
        long total = userMapper.count(username);
        List<User> items = userMapper.page(offset, pageSize, username);
        return new PageResult<>(total, items);
    }
}
