package com.zjweu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TraningRecordPageVO {
    //场景ID
    @ApiModelProperty("训练记录ID")
    private Integer id;

    //场景
    @ApiModelProperty("场景名")
    private String sceneName;

    @ApiModelProperty("总分数")
    private Integer totalScore;

    @ApiModelProperty("时间")
    private LocalDateTime createDate;

    @ApiModelProperty("反馈")
    private String feedback;

}
