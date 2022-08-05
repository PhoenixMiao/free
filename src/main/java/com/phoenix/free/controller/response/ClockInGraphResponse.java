package com.phoenix.free.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ClockInGraphResponse 用于显示最近打卡人数和次数")
public class ClockInGraphResponse {
    @ApiModelProperty("七天打卡次数")
    List<Integer> clockInTimes;

    @ApiModelProperty("七天打卡人数")
    List<Integer> clockInUsers;

    @ApiModelProperty("七天日期")
    List<String> date;
}
