package com.zjweu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String nickname;

    private String password;

    private Integer role;

    private Integer age;

    private String sex;

    private String number;

    private LocalDate joinedDate;

    private String avatar;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer operator;


}
