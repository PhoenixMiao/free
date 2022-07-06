package com.phoenix.free.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.phoenix.free.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class ResultAspect {
    @Around("execution(public * com.phoenix.free.controller.*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
            Object object = proceedingJoinPoint.proceed();
            if(Objects.isNull(object) || !object.getClass().equals(Result.class)){
                object = Result.success(object);
            }
            return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }
}

