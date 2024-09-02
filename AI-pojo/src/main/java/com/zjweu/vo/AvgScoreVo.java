package com.zjweu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AvgScoreVo {

    @ApiModelProperty("日期")
    private LocalDate date;


    @ApiModelProperty("平均分数")
    private Double avg;

}
