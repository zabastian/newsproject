package com.example.newsproject.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String contents;

    public Profile() {}

    public Profile( User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }
}

