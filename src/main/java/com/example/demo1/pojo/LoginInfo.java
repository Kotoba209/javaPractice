package com.example.demo1.pojo;

import jakarta.validation.constraints.NotBlank;

public class LoginInfo {
    @NotBlank(message = "鐢ㄦ埛鍚嶄笉鑳戒负绌?)
    private String username;
    @NotBlank(message = "瀵嗙爜涓嶈兘涓虹┖")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
