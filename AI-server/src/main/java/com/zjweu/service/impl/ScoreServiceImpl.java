package com.zjweu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.zjweu.dto.UserScorePageDTO;
import com.zjweu.mapper.ScoreMapper;
import com.zjweu.po.Score;
import com.zjweu.po.TrainingRecord;
import com.zjweu.service.ScoreService;
import com.zjweu.service.TrainingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {
    @Autowired
    public ScoreMapper scoreMapper;

    @Autowired
    public TrainingRecordService recordService;

    public List<Score> getScoreList(UserScorePageDTO userScorePageDTO)
    {
        PageHelper.startPage(userScorePageDTO.getPage(),userScorePageDTO.getPageSize());
        return null;
    }
    public Score getSubScores(Integer trainingId){
        return scoreMapper.getSubScoresByTrainingId(trainingId);
    }

    @Override
    public void insert(Score score, String sid) {
        save(score);
        TrainingRecord record = recordService.getById(sid);
        record.setScoreId(score.getId());
        recordService.updateById(record);
    }

}
