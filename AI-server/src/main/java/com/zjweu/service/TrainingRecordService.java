package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.po.TrainingRecord;
import com.zjweu.result.PageResult;
import com.zjweu.vo.AvgScoreVo;
import com.zjweu.vo.SceneCounts;
import com.zjweu.vo.TraningRecordPageVO;

import java.time.LocalDate;
import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itcast
 * @since 2024-08-29
 */
public interface TrainingRecordService extends IService<TrainingRecord> {


    PageResult<TraningRecordPageVO> pagequery(TrainingRecordPageDTO trainingRecordPageDTO);

    List<AvgScoreVo> getAvgScore(LocalDate begin, LocalDate end);

    List<AvgScoreVo> getCounts(LocalDate begin, LocalDate end);

    List<SceneCounts> getSceneCounts();

    List<SceneCounts> getSceneAvg();
}
