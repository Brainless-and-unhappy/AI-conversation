package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.po.TrainingRecord;
import com.zjweu.result.PageResult;
import com.zjweu.vo.TraningRecordPageVO;


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
}
