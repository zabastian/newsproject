package com.example.newsproject.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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
