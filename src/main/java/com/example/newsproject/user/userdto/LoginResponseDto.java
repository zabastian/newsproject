package com.example.newsproject.user.userdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {
    private Long userId;
    private String token;

    public LoginResponseDto(Long userId,String token) {
        this.userId = userId;
        this.token = token;
    }
}
