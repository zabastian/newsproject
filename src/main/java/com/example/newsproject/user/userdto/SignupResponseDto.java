package com.example.newsproject.user.userdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupResponseDto {

    private Long userId;
    private String username;
    private String email;
//    private String token;


    public SignupResponseDto(Long userId, String username, String email/*,String token*/){
        this.userId = userId;
        this.username = username;
        this.email = email;
//        this.token = token;
    }

}
