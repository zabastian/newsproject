package com.example.newsproject.common.aop;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //어노테이션일뿐 bean으로 등록할 필요가 없다
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackTime {
}
