package com.example.newsproject.common.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.newsproject.common.exception.newValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    public String encode(String rawPassword) {

        if (!passwordLength(rawPassword)) {
            throw new newValidationException("비밀번호 숫자 오류", HttpStatus.CONFLICT);
        }
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }

    public boolean passwordLength(String password) {
        if (password.length() < 8) {
            return false;
        }
        return true;
    }
}

