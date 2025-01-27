package com.example.newsproject.profile.profileDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileReadDto {
    Long profileId;
    String title;
    String contents;
}
