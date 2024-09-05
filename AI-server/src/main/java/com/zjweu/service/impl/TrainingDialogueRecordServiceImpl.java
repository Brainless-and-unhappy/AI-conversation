package com.zjweu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjweu.mapper.TrainingDialogueRecordMapper;
import com.zjweu.po.TrainingDialogueRecord;
import com.zjweu.service.TrainingDialogueRecordService;
import com.zjweu.vo.TrainingDialogueRecordVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public List<TrainingDialogueRecordVO> getAll(Integer trainingId) {
        List<TrainingDialogueRecord> list = lambdaQuery().eq(trainingId != null, TrainingDialogueRecord::getTrainingId, trainingId).list();
        List<TrainingDialogueRecordVO> vos = BeanUtil.copyToList(list, TrainingDialogueRecordVO.class);

        return vos;
    }
}
