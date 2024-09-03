package com.zjweu.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "用户分页查询返回数据格式")
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
