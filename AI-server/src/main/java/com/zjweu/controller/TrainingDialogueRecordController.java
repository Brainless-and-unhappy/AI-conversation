package com.zjweu.controller;


import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.po.TrainingDialogueRecord;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.service.TrainingDialogueRecordService;
import com.zjweu.vo.TrainingDialogueRecordVO;
import com.zjweu.vo.TraningRecordPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itcast
 */
@Slf4j
@RestController
@RequestMapping("trainingDialogueRecord")
@Api(tags = "详细对话")
public class TrainingDialogueRecordController {

    @Autowired
    private TrainingDialogueRecordService trainingDialogueRecordService;


    @GetMapping("")
    @ApiOperation("获取详细对话记录")
    public Result<List<TrainingDialogueRecordVO>> select(Integer training_id){
        log.info("查询详细对话记录");



        return Result.success(trainingDialogueRecordService.getAll(training_id));
    }
}
