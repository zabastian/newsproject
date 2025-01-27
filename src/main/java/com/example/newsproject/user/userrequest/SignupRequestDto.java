package com.example.newsproject.user.userrequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;


//
//public record SignupRequest(
//        @NotBlank
//        @Email
//        String email,
//        @NotBlank
//        String password
//)
//
//
//{}
public class SignupRequestDto {
    private String username;
    @NotNull
    @Email
    private String email;
    @NotBlank
    private String password;

    public SignupRequestDto(String username,String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}