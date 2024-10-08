package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户分页数据模型")
public class UserPageDTO {

    @ApiModelProperty("用户名")
    private String nickname;

    @ApiModelProperty("用户性别")
    private String sex;

    //页码
    @ApiModelProperty("页码")
    private int page=1;

    @ApiModelProperty("每页显示记录数")
    private int pageSize=10;
}
