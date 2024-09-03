package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.po.TrainingRecord;
import com.zjweu.vo.SceneCounts;
import com.zjweu.vo.TraningRecordPageVO;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TrainingRecordMapper extends BaseMapper<TrainingRecord> {

    Page<TraningRecordPageVO> pageQuery(TrainingRecordPageDTO trainingRecordPageDTO, Integer id);

    Page<TraningRecordPageVO> pageQuery(TrainingRecordPageDTO trainingRecordPageDTO);

    Double getAvgScore(Map map);

    Double getCounts(Map map);

    List<SceneCounts> getSceneCounts(Integer userId);

    List<SceneCounts> getSceneAvg(Integer currentId);
}
