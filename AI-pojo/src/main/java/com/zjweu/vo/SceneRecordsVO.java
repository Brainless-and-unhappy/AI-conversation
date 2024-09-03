package com.zjweu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "场景返回数据格式")
public class SceneRecordsVO {
    //场景ID
    @ApiModelProperty("场景ID")
    private Integer id;

    //场景
    @ApiModelProperty("场景名")
    private String sceneName;

    @ApiModelProperty("最高分数")
    private Integer highestScore;

    @ApiModelProperty("训练次数")
    private Integer trainingCount;

}
