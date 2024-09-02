package com.zjweu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserScorePageDTO {
    //页码
    @ApiModelProperty("页码")
    private int page=1;

    @ApiModelProperty("每页显示记录数")
    private int pageSize=10;
}
