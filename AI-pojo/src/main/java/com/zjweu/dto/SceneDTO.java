package com.zjweu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("场景模型修改")
public class SceneDTO {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("场景详细")

    private String prompt;
    @ApiModelProperty("场景名")

    private String name;
}
