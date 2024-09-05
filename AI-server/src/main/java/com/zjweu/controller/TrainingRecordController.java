package com.zjweu.controller;


import cn.hutool.core.bean.BeanUtil;
import com.zjweu.context.BaseContext;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.po.TrainingRecord;
import com.zjweu.po.User;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.service.TrainingRecordService;
import com.zjweu.vo.AvgScoreVo;
import com.zjweu.vo.SceneCounts;
import com.zjweu.vo.TraningRecordPageVO;
import com.zjweu.vo.UserPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
@RequestMapping("admin/trainingRecord")
@Api(tags = "训练记录")
public class TrainingRecordController {

    @Autowired
    private TrainingRecordService trainingRecordService;

    @GetMapping("")
    @ApiOperation("查询训练记录")
    public Result<PageResult<TraningRecordPageVO>> select(TrainingRecordPageDTO trainingRecordPageDTO){
        log.info("查询训练记录");
        PageResult<TraningRecordPageVO> traningRecordPageVO=trainingRecordService.pagequery(trainingRecordPageDTO);


        return Result.success(traningRecordPageVO);
    }

    @GetMapping("/daily-average-scores")
    @ApiOperation("每日训练平均分数")
    public Result<List<AvgScoreVo>> getAvgScore(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end)
    {
        log.info("每日训练平均分数：{},{}",begin,end);
        return Result.success(trainingRecordService.getAvgScore(begin,end));

    }

    @GetMapping("/daily-counts")
    @ApiOperation("每日训练次数")
    public Result<List<AvgScoreVo>> getCounts(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end)
    {
        log.info("每日训练次数：{},{}",begin,end);
        return Result.success(trainingRecordService.getCounts(begin,end));

    }

    @GetMapping("/scenario-counts")
    @ApiOperation("每场景训练次数")
    public Result<List<SceneCounts>> getSceneCounts()
    {
        log.info("每场景训练次数");
        return Result.success(trainingRecordService.getSceneCounts());
    }
    @GetMapping("/scenario-average-scores")
    @ApiOperation("每场景训练平均分")
    public Result<List<SceneCounts>> getSceneAvg()
    {
        log.info("每场景训练次数");
        return Result.success(trainingRecordService.getSceneAvg());
    }


    @GetMapping("/insert")
    @ApiOperation("插入训练记录")
    public Result<TrainingRecord> insert(Integer scene_id){
        TrainingRecord trainingRecord=trainingRecordService.insert(scene_id);

        return Result.success(trainingRecord);
    }

    @GetMapping("/feedback")
    @ApiOperation("插入训练记录")
    public Result<String> getFeedback(Integer id){
        System.out.println(id);
        TrainingRecord trainingRecord = trainingRecordService.getById(id);

        return Result.success(trainingRecord.getFeedback());
    }


}
