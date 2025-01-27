package com.example.newsproject.profile.profileDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ProfileDto {
    Long userId;
    String title;
    String contents;
}
