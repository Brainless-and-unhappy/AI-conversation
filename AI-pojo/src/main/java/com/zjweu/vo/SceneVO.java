package com.zjweu.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class SceneVO implements Serializable {
    @ApiModelProperty("场景ID")
    private Integer id;
    @ApiModelProperty("场景名")
    private String name;
}
