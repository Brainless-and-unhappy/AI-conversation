package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.po.TrainingRecord;
import com.zjweu.vo.TraningRecordPageVO;


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
}
