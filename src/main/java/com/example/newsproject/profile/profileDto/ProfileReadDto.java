package com.example.newsproject.profile.profileDto;

import com.example.newsproject.common.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileReadDto {
    Long profileId;
    String title;
    String contents;

    public ProfileReadDto(Profile profile){
        this.profileId = profile.getProfileId();
        this.title = profile.getTitle();
        this.contents = profile.getContents();
    }
}
