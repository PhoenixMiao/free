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
@ApiModel("NewlyCreatedUsersGraphResponse 用于显示新近注册的用户数量")
public class NewlyCreatedUsersGraphResponse {
    @ApiModelProperty("七天注册数目")
    List<Integer> stats;

    @ApiModelProperty("七天日期")
    List<String> date;
}
