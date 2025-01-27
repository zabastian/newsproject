package com.example.newsproject.user.userrequest;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter

public class LoginRequestDto{
    private String email;
    private String password;

    public LoginRequestDto(String email,String password){
        this.email=email;
        this.password = password;
    }


}
//public record LoginRequestDto(
//    @NotBlank
//    @Email
//    String email,
//    @NotBlank
//    String password
//)
//{}



