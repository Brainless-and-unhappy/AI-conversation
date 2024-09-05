package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.po.TrainingDialogueRecord;
import com.zjweu.vo.TrainingDialogueRecordVO;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itcast
 * @since 2024-08-29
 */
public interface TrainingDialogueRecordService extends IService<TrainingDialogueRecord> {
    void insert(TrainingDialogueRecord record);

    List<TrainingDialogueRecordVO> getAll(Integer trainingId);
}
