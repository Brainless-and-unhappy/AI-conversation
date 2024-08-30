package com.zjweu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zjweu.constant.MessageConstant;
import com.zjweu.dto.UserLoginDTO;
import com.zjweu.exception.AccountNotFoundException;
import com.zjweu.exception.PasswordErrorException;
import com.zjweu.mapper.UserMapper;
import com.zjweu.po.User;
import com.zjweu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据

        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(username != null, User::getNickname, username);
        User user = getOne(eq);
        //2、处理各种异常情况（用户名不存在、密码不对）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return user;


    }
}
