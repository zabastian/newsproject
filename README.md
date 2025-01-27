Web Config
```
@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final TokenService tokenService;

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CustomFilter(tokenService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public AspectPractice aspectPractice() {
        return new AspectPractice();
    }

```

Gradle

    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'at.favre.lib:bcrypt:0.10.2'
    implementation 'com.auth0:java-jwt:3.3.0'

Create a JWT

```
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
```

Verify a JWT

```
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
            throw new RuntimeException("토큰 검증 실패",ex);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("HMAC256 생성 실패", ex);
        }
    }
}
```
```
@PostMapping("/create")
    public ResponseEntity<ProfileDto> profile(@RequestBody ProfileRequestDto requestDto, HttpServletRequest request){


        Long userId = (Long) request.getAttribute("userId");
        ProfileDto profile = profileService.createProfile(
                userId,
                requestDto.getTitle(),
                requestDto.getContents()
        );
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
```
AOP
```
@Slf4j
@Aspect
public class AspectPractice {

//    //포인트컷
//    @Pointcut("execution(* com.example.newsproject.profile..*(..))")
//    public void profileServiceLayerPointCut() {}

    @Pointcut("@annotation(com.example.newsproject.common.aop.TrackTime)")
    public void trackTimePointCtu() {}

    @Around("trackTimePointCtu()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("BEFORE "); // 해당 메서드 실행되기 전 부가기능
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed(); //핵심 기능 수행되는 부분 --> 횡단관심사
            log.info("AFTER RETURNING");
            log.info("result : {} ", result.getClass());
            return result;
        }
        catch (Throwable e) {
            log.info("AFTER THROWING");
            throw e;
        }
        finally{
            log.info("AFTER");
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            log.info("::: ExecutionTime: {}ms", executionTime);
        }
    }
}

```
```
@TrackTime
    public ProfileDto createProfile(long user,String title,String contents){
        User user1 = userRepository.findById(user)
                .orElseThrow(() -> new newValidationException("id가 맞지 않아요~", HttpStatus.CONFLICT));
        Profile profile = new Profile(user1,title,contents);
        profileRepository.save(profile);
        return new ProfileDto(profile.getUser().getUserId(),profile.getTitle(),profile.getContents());
    }
```

```
Trouble Shooting

Define the Problem

-1.token을 생성할 시 회원가입과 로그인에 필터가 적용이 되어 토큰이없다는 오류 메세지가 나옴
-2.토큰이 제대로 생성되지 않는 문제가 나옴
Analyze the Root Cause

- 1.iswhitelist로 sign,login 추가를 안해 줌
- 2.회원갸입시에 토큰을 생성하는 코드를 서비스단에 넣어 문제가 발생 
Implement the Solution

- 1.iswhitelist를 적용하니 문제가 해결
- 2.회원가입이 아니라 로그인시 토큰을 생성하는 코드를 서비스단에 넣어주니 문제가 해결

