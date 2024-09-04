package com.zjweu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjweu.mapper.TrainingDialogueRecordMapper;
import com.zjweu.po.TrainingDialogueRecord;
import com.zjweu.service.TrainingDialogueRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingDialogueRecordServiceImpl extends ServiceImpl<TrainingDialogueRecordMapper, TrainingDialogueRecord> implements TrainingDialogueRecordService {

    @Override
    public void insert(TrainingDialogueRecord record) {
       Integer num= baseMapper.selectNum(record.getTrainingId());
       record.setOrderNum(num+1);
       record.setDate(LocalDateTime.now());
        System.out.println(record);
       baseMapper.insert(record);
    }
}
