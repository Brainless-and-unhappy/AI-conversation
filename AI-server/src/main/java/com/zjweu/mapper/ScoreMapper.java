package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjweu.context.BaseContext;
import com.zjweu.po.Score;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

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
    public List<Score> getSubScoresByTrainingId(Integer trainingId);
}
