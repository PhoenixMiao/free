package com.phoenix.free.service;

import com.phoenix.free.controller.response.RecordResponse;
import com.phoenix.free.controller.response.UserHealthInfoResponse;

public interface UserHealthInfoService {
    UserHealthInfoResponse getUserHealthInfo(Long userId);
    RecordResponse getRecord(Long userId, boolean isOneDay);
}
