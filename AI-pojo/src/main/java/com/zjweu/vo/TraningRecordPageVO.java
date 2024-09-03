package com.zjweu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "训练返回数据格式")
public class TraningRecordPageVO {
    //场景ID
    @ApiModelProperty("训练记录ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String nickName;

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
