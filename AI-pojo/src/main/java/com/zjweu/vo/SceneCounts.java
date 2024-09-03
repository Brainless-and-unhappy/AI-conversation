package com.zjweu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "每个场景训练次数返回数据格式")
public class SceneCounts {
    @ApiModelProperty("场景名")
    private String sceneName;


    @ApiModelProperty("平均分数")
    private Double avg;
}
