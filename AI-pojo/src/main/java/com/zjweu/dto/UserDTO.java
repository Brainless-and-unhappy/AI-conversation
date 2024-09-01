package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
@Data
@ApiModel(description = "修改用户时传递的数据模型")
public class UserDTO {
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("手机号")
    private String number;
    @ApiModelProperty("加入时间")
    private LocalDate joinedDate;
}
