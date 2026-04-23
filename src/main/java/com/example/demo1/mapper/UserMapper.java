package com.example.demo1.mapper;

import com.example.demo1.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id, username, nickname from user")
    List<User> findAll();

    @Select("select id, username, nickname from user where id=#{id}")
    User findById(Integer  id);

    @Insert("insert into user(username, nickname) values(#{username}, #{nickname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("update user set username = #{username}, nickname = #{nickname} where id = #{id}")
    int updateById(User user);

    @Delete("delete from user where id = #{id}")
    int deleteById(Integer id);
}
