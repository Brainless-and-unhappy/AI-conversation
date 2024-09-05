package com.zjweu.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "场景记录返回数据格式")
public class TrainingDialogueRecordVO implements Serializable {
    private String content;

    private Boolean speaker;
    //private Boolean speaker;

    private LocalDateTime date;

}
