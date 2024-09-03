package com.zjweu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjweu.context.BaseContext;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.mapper.SceneMapper;
import com.zjweu.mapper.TrainingRecordMapper;
import com.zjweu.mapper.UserMapper;
import com.zjweu.po.TrainingRecord;
import com.zjweu.po.User;
import com.zjweu.result.PageResult;
import com.zjweu.service.TrainingRecordService;
import com.zjweu.vo.AvgScoreVo;
import com.zjweu.vo.SceneCounts;
import com.zjweu.vo.TraningRecordPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<AvgScoreVo> getAvgScore(LocalDate begin, LocalDate end) {
        //当前集合用于存放从begin，end范围内的每天日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

//        for (LocalDate i = begin;i.equals(end);i.plusDays(1))
        while (!begin.equals(end)){
            //日期计算
            begin=begin.plusDays(1);
            dateList.add(begin);
        }
        List<AvgScoreVo> avgScoreVoList = new ArrayList<>();
        for (LocalDate date:dateList) {
            //查询date日期对应的数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin",beginTime);
            map.put("end",endTime);
            map.put("id",BaseContext.getCurrentId());
            Double avgScore=baseMapper.getAvgScore(map);
            AvgScoreVo avgScoreVo = new AvgScoreVo();
            avgScoreVo.setAvg(avgScore);
            avgScoreVo.setDate(date);
            avgScoreVoList.add(avgScoreVo);

        }
        return avgScoreVoList;
    }

    @Override
    public List<AvgScoreVo> getCounts(LocalDate begin, LocalDate end) {
        //当前集合用于存放从begin，end范围内的每天日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

//        for (LocalDate i = begin;i.equals(end);i.plusDays(1))
        while (!begin.equals(end)){
            //日期计算
            begin=begin.plusDays(1);
            dateList.add(begin);
        }
        List<AvgScoreVo> avgScoreVoList = new ArrayList<>();
        for (LocalDate date:dateList) {
            //查询date日期对应的数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin",beginTime);
            map.put("end",endTime);
            map.put("id",BaseContext.getCurrentId());
            Double avgScore=baseMapper.getCounts(map);
            AvgScoreVo avgScoreVo = new AvgScoreVo();
            avgScoreVo.setAvg(avgScore);
            avgScoreVo.setDate(date);
            avgScoreVoList.add(avgScoreVo);

        }
        return avgScoreVoList;
    }

    @Override
    public List<SceneCounts> getSceneCounts() {
        List<SceneCounts> list=baseMapper.getSceneCounts(BaseContext.getCurrentId());
        return list;
    }

    @Override
    public List<SceneCounts> getSceneAvg() {
        List<SceneCounts> list=baseMapper.getSceneAvg(BaseContext.getCurrentId());
        return list;
    }
}
