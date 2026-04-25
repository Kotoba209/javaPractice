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
            throw new BusinessException("鐢ㄦ埛涓嶅瓨鍦?);
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        User existUser = userMapper.findUserByName(user.getUsername());
        if (existUser != null) {
            throw new BusinessException("鐢ㄦ埛鍚嶅凡瀛樺湪");
        }
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User dbdUser = userMapper.findById(user.getId());
        if (dbdUser == null) {
            throw new BusinessException("鐢ㄦ埛涓嶅瓨鍦?);
        }
        User existUser = userMapper.findUserByName(user.getUsername());
        if (existUser != null && !existUser.getId().equals(user.getId())) {
            throw new BusinessException("鐢ㄦ埛鍚嶅凡瀛樺湪");

        }
        dbdUser.setUsername(user.getUsername());
        dbdUser.setNickname(user.getNickname());
        userMapper.updateById(dbdUser);
        return dbdUser;
    }

    @Override
    public void deleteUser(Integer id) {
        User dbdUser = userMapper.findById(id);
        if (dbdUser == null) {
            throw new BusinessException("鐢ㄦ埛涓嶅瓨鍦?);
        }
        userMapper.deleteById(id);
    }

    @Override
    public PageResult<User> page(Integer pageNum, Integer pageSize, String username, String nickname, String sort) {
        if (!sort.equalsIgnoreCase("desc") && !sort.equalsIgnoreCase("asc")) {
            throw new BusinessException("鎺掑簭鍙傛暟鍙兘鏄?asc 鎴?desc");
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
