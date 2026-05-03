package com.example.demo1.service.impl;

import com.example.demo1.exception.BusinessException;
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
        User user = userMapper.findById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        User existUser = userMapper.findUserByName(user.getUsername());
        if (existUser != null) {
            throw new BusinessException("用户名已存在");
        }
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User dbUser = userMapper.findById(user.getId());
        if (dbUser == null) {
            throw new BusinessException("用户不存在");
        }
        User existUser = userMapper.findUserByName(user.getUsername());
        if (existUser != null && !existUser.getId().equals(user.getId())) {
            throw new BusinessException("用户名已存在");
        }
        dbUser.setUsername(user.getUsername());
        dbUser.setNickname(user.getNickname());
        userMapper.updateById(dbUser);
        return dbUser;
    }

    @Override
    public void deleteUser(Integer id) {
        User dbUser = userMapper.findById(id);
        if (dbUser == null) {
            throw new BusinessException("用户不存在");
        }
        userMapper.deleteById(id);
    }

    @Override
    public PageResult<User> page(Integer pageNum, Integer pageSize, String username, String nickname, String sort) {
        String normalizedSort = sort == null ? "asc" : sort.toLowerCase();
        if (!normalizedSort.equals("asc") && !normalizedSort.equals("desc")) {
            throw new BusinessException("排序参数只能是 asc 或 desc");
        }
        Integer offset = (pageNum - 1) * pageSize;
        long total = userMapper.count(username, nickname);
        List<User> items = userMapper.page(offset, pageSize, username, nickname, sort);
        return new PageResult<>(total, items);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return userMapper.getPasswordByUsername(username);
    }
}
