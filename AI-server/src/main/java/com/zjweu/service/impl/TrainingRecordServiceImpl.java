package com.zjweu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjweu.context.BaseContext;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.mapper.TrainingRecordMapper;
import com.zjweu.mapper.UserMapper;
import com.zjweu.po.TrainingRecord;
import com.zjweu.po.User;
import com.zjweu.result.PageResult;
import com.zjweu.service.TrainingRecordService;
import com.zjweu.vo.TraningRecordPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TrainingRecordServiceImpl extends ServiceImpl<TrainingRecordMapper, TrainingRecord> implements TrainingRecordService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public PageResult<TraningRecordPageVO> pagequery(TrainingRecordPageDTO trainingRecordPageDTO) {

        User user = userMapper.selectById(BaseContext.getCurrentId());
        Page<TraningRecordPageVO> page =new Page<>();
        if(user.getRole().equals(1)){
            PageHelper.startPage(trainingRecordPageDTO.getPage(),trainingRecordPageDTO.getPageSize());
            trainingRecordPageDTO.setUserId(user.getId());
            page= baseMapper.pageQuery(trainingRecordPageDTO);
        }else {
            PageHelper.startPage(trainingRecordPageDTO.getPage(),trainingRecordPageDTO.getPageSize());
            page= baseMapper.pageQuery(trainingRecordPageDTO);
        }
        PageResult result= new PageResult();
        result.setRecords(page.getResult());
        System.out.println(page.getTotal());
        result.setTotal(page.getTotal());



        return result;
    }
}
