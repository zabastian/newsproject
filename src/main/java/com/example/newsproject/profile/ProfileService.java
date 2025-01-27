package com.example.newsproject.profile;

import com.example.newsproject.common.aop.TrackTime;
import com.example.newsproject.common.entity.Profile;
import com.example.newsproject.common.entity.User;
import com.example.newsproject.common.exception.newValidationException;
import com.example.newsproject.profile.profileDto.ProfileDto;
import com.example.newsproject.profile.profileDto.ProfileReadDto;
import com.example.newsproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @TrackTime
    public ProfileDto createProfile(long user,String title,String contents){
        User user1 = userRepository.findById(user)
                .orElseThrow(() -> new newValidationException("id가 맞지 않아요~", HttpStatus.CONFLICT));
        Profile profile = new Profile(user1,title,contents);
        profileRepository.save(profile);
        return new ProfileDto(profile.getUser().getUserId(),profile.getTitle(),profile.getContents());
    }

    public ProfileReadDto readProfile(long profileId){
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new newValidationException("프로필 생성 해주세요",HttpStatus.CONFLICT));
//        System.out.println("프로필 아이디 " + profile.getProfileId());
        return new ProfileReadDto(profile.getProfileId(),profile.getTitle(),profile.getContents());
    }

    public ProfileReadDto updateProfile(long profileId,String title,String contents){
        Profile profile1 = profileRepository.findById(profileId)
                .orElseThrow(()-> new newValidationException("프로필 아이디가 비었습니다.",HttpStatus.CONFLICT));

        profile1.setTitle(title);
        profile1.setContents(contents);

        profileRepository.save(profile1);
//        Profile updateprofile = new Profile(profile1,title,contents);
        return new ProfileReadDto(profile1.getProfileId(),profile1.getTitle(),profile1.getContents());
    }

    public String deleteProfile(long profileId){
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new newValidationException("프로필 아이디 비었습니다.",HttpStatus.CONFLICT));
        profileRepository.delete(profile);
        return "프로필 삭제";
    }



}
