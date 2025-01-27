Gradle

    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'at.favre.lib:bcrypt:0.10.2'
    implementation 'com.auth0:java-jwt:3.3.0'

Create a JWT

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

Verify a JWT

