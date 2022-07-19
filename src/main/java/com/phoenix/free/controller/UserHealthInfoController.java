package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.response.RecordResponse;
import com.phoenix.free.controller.response.UserHealthInfoResponse;
import com.phoenix.free.service.UserHealthInfoService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
public class UserHealthInfoController {
    @Autowired
    private UserHealthInfoService userHealthInfoService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @Deprecated
    @GetMapping("/info")
    @ApiOperation(value = "查看当前用户健康信息",response = UserHealthInfoResponse.class)
    public Object getUserHealthInfo(){
        Long id = sessionUtils.getUserId();
        return userHealthInfoService.getUserHealthInfo(id);
    }

    @Auth
    @GetMapping("/weekly")
    @ApiOperation(value = "查看当前用户本周健康记录",response = RecordResponse.class)
    public Object getUserWeeklyHealthInfo(){
        Long id = sessionUtils.getUserId();
        return userHealthInfoService.getRecord(id, false);
    }

    @Auth
    @GetMapping("/daily")
    @ApiOperation(value = "查看当前用户今日健康记录",response = RecordResponse.class)
    public Object getUserDailyHealthInfo(){
        Long id = sessionUtils.getUserId();
        return userHealthInfoService.getRecord(id, true);
    }

    @Auth
    @GetMapping("/echarts")
    @ApiOperation(value = "查看当前用户七日健康记录统计图",response = RecordResponse.class)
    public Object getSevenDaysGraph(){
        Long id = sessionUtils.getUserId();
        return userHealthInfoService.getSevenDaysRecord(id);
    }
}
