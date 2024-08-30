package com.zjweu.controller;


import com.zjweu.constant.JwtClaimsConstant;
import com.zjweu.dto.UserLoginDTO;
import com.zjweu.po.User;
import com.zjweu.properties.JwtProperties;
import com.zjweu.result.Result;
import com.zjweu.service.UserService;
import com.zjweu.utils.JwtUtil;
import com.zjweu.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  用户
 * </p>
 *
 *
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
@Api(tags = "用户接口")
public class UserController {


    private final UserService userService;

    private final JwtProperties jwtProperties;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("员工登录：{}",userLoginDTO);
        User user= userService.login(userLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        UserVO userVO = UserVO.builder().userid(user.getId())
                .token(token)
                .nickname(user.getNickname())
                .role(user.getRole()).build();
        return Result.success(userVO);
    }

    @ApiOperation(value = "测试")
    @PostMapping("/test")
    public Result<String> tes1t(){

        return Result.success("11111");
    }



}
