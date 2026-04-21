package com.example.demo1.pojo;

import com.example.demo1.validation.AddGroup;
import com.example.demo1.validation.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Integer id;
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    @NotBlank(message = "昵称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Size(min = 2, max = 5, message = "昵称长度必须在2-5之间", groups = {AddGroup.class, UpdateGroup.class})
    private String nickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
