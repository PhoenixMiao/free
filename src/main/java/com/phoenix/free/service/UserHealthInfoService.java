package com.phoenix.free.service;

import com.phoenix.free.controller.response.UserHealthInfoResponse;
import com.phoenix.free.controller.response.WeeklyHealthInfoResponse;

public interface UserHealthInfoService {
    UserHealthInfoResponse getUserHealthInfo(Long userId);
    WeeklyHealthInfoResponse getWeeklyHealthInfo(Long userId);
}
