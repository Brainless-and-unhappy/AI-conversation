package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户注册时传递的数据模型")
public class RegisterDTO {

    @ApiModelProperty("用户名")
    private String nickname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("手机号")
    private String number;
}
