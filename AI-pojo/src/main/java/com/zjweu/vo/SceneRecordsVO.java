package com.zjweu.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class SceneRecordsVO {
    //场景ID
    @ApiModelProperty("场景ID")
    private Integer id;

    //场景
    @ApiModelProperty("场景名")
    private String seceneName;

    @ApiModelProperty("最高分数")
    private Integer highestScore;

    @ApiModelProperty("训练次数")
    private Integer trainingCount;

}
