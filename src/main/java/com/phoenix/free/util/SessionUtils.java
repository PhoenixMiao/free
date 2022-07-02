package com.phoenix.free.util;

import com.phoenix.free.common.CommonConstants;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.dto.SessionData;
import com.phoenix.free.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;


/**
 * @author yannis
 * @version 2020/8/1 18:38
 */
@Component
public class SessionUtils {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    private UserMapper userMapper;

    public Long getUserId(){
        return Optional
                .ofNullable(getSessionData())
                .orElse(new SessionData())
                .getId();
    }


    public SessionData getSessionData() throws CommonException{
        String key = request.getHeader(CommonConstants.SESSION);
        if(key == null) throw new CommonException(CommonErrorCode.NEED_SESSION_ID);
        if(!redisUtil.hasKey(key)) throw new CommonException(CommonErrorCode.SESSION_IS_INVALID);
        if(redisUtil.isExpire(key)){
            redisUtil.del(key);
            throw new CommonException(CommonErrorCode.LOGIN_HAS_OVERDUE);
        }

        return (SessionData) redisUtil.get(key);

    }

    public void setSessionId(String sessionId){
        response.setHeader(CommonConstants.SESSION,sessionId);
    }

    public String generateSessionId() {
        String sessionId = UUID.randomUUID().toString();
        response.setHeader(CommonConstants.SESSION, sessionId);
        return sessionId;
    }

    public void ChangeContentType(){
        response.setHeader("Content-Type","application/json");
    }
}
