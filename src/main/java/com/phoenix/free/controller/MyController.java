package com.phoenix.free.controller;

import com.phoenix.free.util.SessionUtils;
import com.phoenix.free.dto.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenix
 * @version 2022/1/19 19:21
 */
@RestController
public class MyController {

    @Autowired
    private SessionUtils sessionUtils;

    @GetMapping("/get")
    @Deprecated
    public Object get(){
        sessionUtils.getUserId();
        SessionData sessionData = sessionUtils.getSessionData();
        return null;
    }

}
