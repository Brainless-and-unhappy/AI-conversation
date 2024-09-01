package com.zjweu.controller;


import cn.hutool.core.bean.BeanUtil;
import com.zjweu.constant.JwtClaimsConstant;
import com.zjweu.context.BaseContext;
import com.zjweu.dto.RegisterDTO;
import com.zjweu.dto.UserDTO;
import com.zjweu.dto.UserLoginDTO;
import com.zjweu.dto.UserPageDTO;
import com.zjweu.po.User;
import com.zjweu.properties.JwtProperties;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.service.UserService;
import com.zjweu.utils.JwtUtil;
import com.zjweu.vo.UserPageVO;
import com.zjweu.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        return getUserJwt(user);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public  Result<UserVO> register(@RequestBody RegisterDTO registerDTO){
        log.info("用户注册 {}",registerDTO);
        User user=userService.register(registerDTO);


        return getUserJwt(user);
    }

    private Result<UserVO> getUserJwt(User user) {
        //获取jwt令牌
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

    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(UserPageDTO userPageDTO){
        log.info("员工分页查询{}",userPageDTO);
        PageResult<UserVO> pagequery = userService.pagequery(userPageDTO);
        return Result.success(pagequery);

    }
    @DeleteMapping("")
    @ApiOperation("员工删除")
    public Result delectById(@RequestParam List<Integer> ids){
        userService.removeByIds(ids);
        return Result.success();
    }

    /*
     * 根据id查询员工
     * */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户信息")
    public Result<UserPageVO> getById(@PathVariable  Long id){
        System.out.println(id);
        User user = userService.getById(id);
        UserPageVO userVO = BeanUtil.copyProperties(user, UserPageVO.class);
        return Result.success(userVO);
    }

    @GetMapping("")
    @ApiOperation("查询当前用户信息")
    public Result<UserPageVO> getBynow(){
        Integer id = BaseContext.getCurrentId();
        System.out.println(id);
        User user = userService.getById(id);
        UserPageVO userVO = BeanUtil.copyProperties(user, UserPageVO.class);
        return Result.success(userVO);
    }

    @PutMapping()
    @ApiOperation("修改员工信息")
    public Result updateById(@RequestBody UserDTO userDTO){
        userService.updateById1(userDTO);
        return Result.success();
    }

    @ApiOperation(value = "测试")
    @PostMapping("/test")
    //test
    public Result<String> tes1t(){

        return Result.success("11111");
    }




}
