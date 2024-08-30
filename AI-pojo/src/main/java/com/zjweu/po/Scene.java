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
@TableName("Scene")
public class Scene implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String prompt;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer operator;


}
