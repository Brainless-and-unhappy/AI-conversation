package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("场景分页模型")
public class ScenePageDTO {


    //页码
    @ApiModelProperty("页码")
    private int page=1;

    @ApiModelProperty("每页显示记录数")
    private int pageSize=10;

    @ApiModelProperty("场景名")
    private String name;

}
