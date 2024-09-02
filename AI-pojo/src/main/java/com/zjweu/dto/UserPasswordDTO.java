package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户修改密码时传递的数据模型")
public class UserPasswordDTO {
    @ApiModelProperty("新密码")
    private String newPassword;

    @ApiModelProperty("密码")
    private String oldPassword;

}
