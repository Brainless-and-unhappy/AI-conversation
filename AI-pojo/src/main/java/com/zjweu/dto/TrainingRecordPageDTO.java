package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "训练记录分页数据模型")
public class TrainingRecordPageDTO {

    @ApiModelProperty("用户名")
    private String nickname;

    @ApiModelProperty("场景id")
    private Integer sceneId;

    @ApiModelProperty("用户id")
    private Integer userId;

    //页码
    @ApiModelProperty("页码")
    private int page=1;

    @ApiModelProperty("每页显示记录数")
    private int pageSize=10;
}
