package com.example.newsproject.user;

import com.example.newsproject.common.jwt.TokenService;
import com.example.newsproject.user.userdto.LoginResponseDto;
import com.example.newsproject.user.userdto.SignupResponseDto;
import com.example.newsproject.user.userrequest.LoginRequestDto;
import com.example.newsproject.user.userrequest.SignupRequestDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto request) {
        SignupResponseDto signupResponseDto =userService.signupUserService(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        return new ResponseEntity<>(signupResponseDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request){
        LoginResponseDto loginResponse = userService.loginUserService(
                requestDto.getEmail(),
                requestDto.getPassword()
        );
//        Long userId = (long) request.getAttribute("userId");
//        request.setAttribute("userId",loginResponse);

//        LoginResponseDto responseDto = new LoginResponseDto(loginResponse);

        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Long> login(@RequestBody LoginRequestDto requestDto, HttpSession session){
//        Long loginResponse = userService.loginUserService(
//                requestDto.getEmail(),
//                requestDto.getPassword()
//        );
//        session.setAttribute("userId",loginResponse);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setMaxAge(0);  // 쿠키 만료
        cookie.setPath("/");  // 쿠키 경로 설정
        response.addCookie(cookie);

        return new ResponseEntity<>("로그아웃",HttpStatus.OK);
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpSession session){
//        session.invalidate();
//        return new ResponseEntity<>("로그아웃",HttpStatus.OK);
//    }


}
