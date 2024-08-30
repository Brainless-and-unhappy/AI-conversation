package com.zjweu.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserPageVO {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private String nickname;
    private Integer role;
    private Integer age;
    private String sex;

    private String number;
    private LocalDate joinedDate;
    private String avatar;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer operator;
}
