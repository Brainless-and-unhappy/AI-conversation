package com.zjweu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("Training_Record")
public class TrainingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer sceneId;

    private Integer scoreId;

    private String feedback;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;


}
