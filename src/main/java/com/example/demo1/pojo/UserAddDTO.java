package com.example.demo1.pojo;

import com.example.demo1.validation.AddGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserAddDTO {
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class})
    private String username;
    @NotBlank(message = "昵称不能为空", groups = {AddGroup.class})
    @Size(min = 2, max = 5, message = "昵称长度必须在2-5之间", groups = {AddGroup.class})
    private String nickname;

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
