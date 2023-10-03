package com.example.login.dto;
import com.example.login.entity.User;
import lombok.Data;

@Data
public class SignupRequestDto {
    private String email;
    private String username;
    private String password;

    public User toUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}