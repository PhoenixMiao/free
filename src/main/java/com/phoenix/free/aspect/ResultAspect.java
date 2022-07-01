//package com.phoenix.free.aspect;
//
//import com.phoenix.free.common.CommonErrorCode;
//import com.phoenix.free.common.CommonException;
//import com.phoenix.free.common.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
///**
// * @author yannis
// * @version 2020/8/1 18:46
// */
//
//import org.aspectj.lang.annotation.Around;
//
//@Aspect
//@Component
//@Slf4j
//public class ResultAspect {
//
//
//    @Around("execution(public * com.phoenix.free.controller.*.*(..))")
//    public Result doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//
//            Object object = proceedingJoinPoint.proceed();
//            return Result.success(object);
//
//
//    }
//
//
//}
//
