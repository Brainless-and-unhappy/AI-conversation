package com.zjweu.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "场景列表返回数据格式")
public class SceneVO implements Serializable {
    @ApiModelProperty("场景ID")
    private Integer id;
    @ApiModelProperty("场景名")
    private String name;
}
