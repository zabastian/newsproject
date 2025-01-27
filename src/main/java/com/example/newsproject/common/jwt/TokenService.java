package com.example.newsproject.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.RequestingUserName;
import java.io.UnsupportedEncodingException;

@Component
public class TokenService {

//    private static final String SECRETE = "secrete";
//    private static final String SECRETE = "secrete";
        private static final String BEARER_PREFIX = "Bearer";



    public String createToken(Long userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("asdafgasdawdasfagasd");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(String.valueOf(userId))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public long verifyToken(String bearerToken) {
//        if (token == null || token.isEmpty()) {
//            throw new RuntimeException("Authorization token is required");
//        }
//        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("asdafgasdawdasfagasd");

            String token = bearerToken.substring(BEARER_PREFIX.length()).trim(); //bearerTOken추출

            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("auth0")
                    // reusable verifier instance
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            String userId = decodedJWT.getSubject();

//            return decodedJWT;
            return Long.parseLong(userId);

        } catch (JWTVerificationException ex) {
            // Invalid signature/claims
            throw new RuntimeException("토큰없음",ex);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("토큰없음2", ex);
        }
    }
}
/*public long verifyToken(String token) {
//        if (token == null || token.isEmpty()) {
//            throw new RuntimeException("Authorization token is required");
//        }
//        DecodedJWT decodedJWT;
    try {
        Algorithm algorithm = Algorithm.HMAC256("asdafgasdawdasfagasd");
        JWTVerifier verifier = JWT.require(algorithm)
                // specify any specific claim validations
                .withIssuer("auth0")
                // reusable verifier instance
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        String userId = decodedJWT.getSubject();

//            return decodedJWT;
        return Long.parseLong(userId);

    } catch (JWTVerificationException ex) {
        // Invalid signature/claims
        throw new RuntimeException("토큰없음",ex);
    } catch (UnsupportedEncodingException ex) {
        throw new RuntimeException("토큰없음2", ex);
    }
}
}*/ //token

