package com.zjweu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "每日平均分数返回数据格式")
public class AvgScoreVo {

    @ApiModelProperty("日期")
    private LocalDate date;


    @ApiModelProperty("平均分数")
    private Double avg;

}
