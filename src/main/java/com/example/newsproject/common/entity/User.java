package com.example.newsproject.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private String deleted;
    private String encodePassword;

    public User(String username,String email,String password){
        this.username = username;
        this.email= email;
        this.password = password;
    }

//    public User(String email,String password) {
//        this.email = email;
//        this.password = password;
//    }


}
