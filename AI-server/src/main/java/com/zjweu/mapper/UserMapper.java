package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjweu.annotation.AutoFill;
import com.zjweu.enumeration.OperationType;
import com.zjweu.po.User;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface UserMapper extends BaseMapper<User> {


    @AutoFill(value = OperationType.INSERT)
    public void insertRegister(User user);
}
