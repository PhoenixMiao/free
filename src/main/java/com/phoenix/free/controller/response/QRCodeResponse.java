package com.phoenix.free.controller.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("QRCodeResponse 获取二维码接口返回体")
public class QRCodeResponse {
    @ApiModelProperty("管理员令牌")
    private String token;

    @ApiModelProperty("URL")
    private String url;
}
