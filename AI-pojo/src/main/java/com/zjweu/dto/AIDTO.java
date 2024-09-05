package com.zjweu.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("AI返回模型")
public class AIDTO {


    private Integer totalScore;

    private Integer listenScore;

    private Integer apologyScore;

    private Integer satisfactionScore;

    private Integer thanksScore;

    private String feedback;

}
