package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.dto.RegisterDTO;
import com.zjweu.dto.UserLoginDTO;
import com.zjweu.dto.UserPageDTO;
import com.zjweu.po.User;
import com.zjweu.result.PageResult;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itcast
 * @since 2024-08-29
 */
public interface UserService extends IService<User> {

    User login(UserLoginDTO userLoginDTO);

    User register(RegisterDTO registerDTO);

    PageResult pagequery(UserPageDTO userPageDTO);
}
