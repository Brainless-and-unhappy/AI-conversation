package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "修改当前用户时传递的数据模型")
public class UserNowDTO {

    @ApiModelProperty("图片路径")
    private String image;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("手机号")
    private String number;

}
