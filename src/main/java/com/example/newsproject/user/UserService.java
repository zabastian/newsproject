package com.example.newsproject.user;

import com.example.newsproject.common.filter.PasswordEncoder;
import com.example.newsproject.common.entity.User;
import com.example.newsproject.common.exception.newValidationException;
import com.example.newsproject.common.jwt.TokenService;
import com.example.newsproject.user.userdto.LoginResponseDto;
import com.example.newsproject.user.userdto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public SignupResponseDto signupUserService(String username, String email, String password){
        String encodePassword = passwordEncoder.encode(password);
        User user = new User(username,email,encodePassword);
        User savedUser = userRepository.save(user);
//        String token = tokenService.createToken(userId);

        return new SignupResponseDto(savedUser.getUserId(),savedUser.getUsername(),savedUser.getEmail()/*,token*/);
    }

    public LoginResponseDto loginUserService(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new newValidationException("사용자를 찾을 수 없습니다.",HttpStatus.CONFLICT));
        if(passwordEncoder.matches(password, user.getPassword())){
            Long userId = user.getUserId();
            String token = tokenService.createToken(userId);
            return new LoginResponseDto(userId,token);
        }
        else throw new newValidationException("비밀번호 일치하지 않음", HttpStatus.CONFLICT);
    }

//    public Long loginUserService(String email, String password){
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new newValidationException("사용자를 찾을 수 없습니다.",HttpStatus.CONFLICT));
//        if(passwordEncoder.matches(password, user.getPassword())){
//            return user.getUserId();
//        }
//        else throw new newValidationException("비밀번호 일치하지 않음", HttpStatus.CONFLICT);
//    }

}
