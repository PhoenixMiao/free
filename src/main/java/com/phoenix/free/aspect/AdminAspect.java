package com.phoenix.free.aspect;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.dto.SessionData;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AdminAspect {

    @Autowired
    SessionUtils sessionUtil;

    @Around("@annotation(com.phoenix.free.annotation.Admin)")
    public Object doAroundAdmin(ProceedingJoinPoint joinPoint) throws Throwable {

        SessionData sessionData = sessionUtil.getSessionData();

        AssertUtil.isNotNull(sessionData, CommonErrorCode.INVALID_SESSION);

        AssertUtil.isTrue(sessionData.getIsAdmin() == 1 || sessionData.getIsAdmin() == 2,CommonErrorCode.USER_NOT_ADMIN);

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        Auth annotation = method.getAnnotation(Auth.class);

        //log
        log.error("------------");
        log.error("operator: " + sessionData.getId());
        log.error("operation: " + method.getName());

        return joinPoint.proceed();
    }
}
