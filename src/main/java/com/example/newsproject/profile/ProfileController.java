package com.example.newsproject.profile;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.newsproject.common.jwt.TokenService;
import com.example.newsproject.profile.profileDto.ProfileDto;
import com.example.newsproject.profile.profileDto.ProfileReadDto;
import com.example.newsproject.profile.profileRequest.ProfileRequestDto;
import com.example.newsproject.profile.profileRequest.ProfileUpdateRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<ProfileDto> profile(@RequestBody ProfileRequestDto requestDto, HttpServletRequest request){

//        String token = request.getHeader("Authorization");
//        long userId = tokenService.verifyToken(token);

        Long userId = (Long) request.getAttribute("userId");
        ProfileDto profile = profileService.createProfile(
                userId,
                requestDto.getTitle(),
                requestDto.getContents()
        );
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
//    @PostMapping("/create")
//    public ResponseEntity<ProfileDto> profile(@RequestBody ProfileRequestDto requestDto, HttpSession session){
//
//        Long userId = (Long) session.getAttribute("userId");
//        ProfileDto profile = profileService.createProfile(
//                userId,
//                requestDto.getTitle(),
//                requestDto.getContents()
//        );
//        return new ResponseEntity<>(profile, HttpStatus.OK);
//    }
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileReadDto> profile_read(@PathVariable Long profileId){
        return new ResponseEntity<>(profileService.readProfile(profileId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfileReadDto>> profileAllRead(){
        return new ResponseEntity<>(profileService.readAllProfile(),HttpStatus.OK);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileReadDto> profile_update(@PathVariable Long profileId, @RequestBody ProfileUpdateRequestDto requestDto){
        ProfileReadDto profile = profileService.updateProfile(
                profileId,
                requestDto.getTitle(),
                requestDto.getContents()
        );
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> profile_delete(@PathVariable Long profileId){
        String isDeleted = profileService.deleteProfile(
                profileId
        );
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }



}
