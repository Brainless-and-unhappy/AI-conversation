package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.dto.*;
import com.zjweu.po.User;
import com.zjweu.result.PageResult;

import java.util.List;


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

    void updateById1(UserDTO userDTO);

    void updateNow(UserNowDTO userDTO);

    void updateps(UserPasswordDTO password);

    void delete(List<Integer> ids);
}
