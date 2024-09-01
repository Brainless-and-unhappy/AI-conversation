package com.zjweu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjweu.constant.MessageConstant;
import com.zjweu.constant.RoleConstant;
import com.zjweu.context.BaseContext;
import com.zjweu.dto.*;
import com.zjweu.exception.AccountNotFoundException;
import com.zjweu.exception.AlreadExistException;
import com.zjweu.exception.AlreadExistNumberException;
import com.zjweu.exception.PasswordErrorException;
import com.zjweu.mapper.UserMapper;
import com.zjweu.po.User;
import com.zjweu.result.PageResult;
import com.zjweu.service.UserService;
import com.zjweu.vo.UserPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @Autowired
    public UserMapper userMapper;
    //登录
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getNickname();
        String password = userLoginDTO.getPassword();
        if(BeanUtil.isEmpty(username) ||  BeanUtil.isEmpty(password))
        {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);

        }


        //1、根据用户名查询数据库中的数据

        //LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(username != null, User::getNickname, username);
        User user = selectbynickname(username);

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
    //查询
    private User selectbynickname(String nickname){

        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(nickname != null, User::getNickname, nickname);
        User user = getOne(eq);
        return user;
    }
    //注册
    @Override
    public User register(RegisterDTO registerDTO) {
        User user = selectbynickname(registerDTO.getNickname());

        if(BeanUtil.isNotEmpty(user)){
            throw new AlreadExistException(MessageConstant.ALREADY_EXISTS);
        }
        user = getOne(new QueryWrapper<User>().lambda().eq(registerDTO.getNumber() != null, User::getNumber, registerDTO.getNumber()));

        if(BeanUtil.isNotEmpty(user)){
            throw new AlreadExistNumberException(MessageConstant.ALREADY_EXISTS_number);
        }
        System.out.println(user);
        user = new User();
        BeanUtils.copyProperties(registerDTO,user);
        user.setJoinedDate(LocalDate.now());
        user.setRole(RoleConstant.ENABLE);
        user.setName("username");
        baseMapper.insert(user);
        return user;
    }
    //分页查询
    @Override
    public PageResult pagequery(UserPageDTO userPageDTO) {
        PageHelper.startPage(userPageDTO.getPage(),userPageDTO.getPageSize());
        User user = new User();
        BeanUtil.copyProperties(userPageDTO,user);
        Page<User> page= baseMapper.pageQuery(user);
        PageResult<UserPageVO> result = new PageResult<>();
        List<User> users = page.getResult();
        List<UserPageVO> userPageVOS = BeanUtil.copyToList(users, UserPageVO.class);
        result.setRecords(userPageVOS);
        result.setTotal(page.getTotal());
        return result;
    }
    //修改用户
    @Override
    public void updateById1(UserDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        user.setId(BaseContext.getCurrentId());
        userMapper.updateById1(user);
    }

    @Override
    public void updateNow(UserNowDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        user.setId(BaseContext.getCurrentId());
        userMapper.updateById1(user);
    }
}
