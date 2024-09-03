package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjweu.po.Score;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */

public interface ScoreMapper extends BaseMapper<Score> {
    public List<Score> getScoreList(Integer userId);
    public Score getSubScoresByTrainingId(Integer trainingId);
}
