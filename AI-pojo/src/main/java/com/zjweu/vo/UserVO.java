package com.zjweu.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录返回数据格式")
public class UserVO implements Serializable {
    @ApiModelProperty("主键值")
    private Integer userid;

    @ApiModelProperty("用户名")
    private String nickname;


    @ApiModelProperty("jwt令牌")
    private String token;

    @ApiModelProperty("类别")
    private Integer role;

}
