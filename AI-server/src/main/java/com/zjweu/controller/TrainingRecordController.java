package com.zjweu.controller;


import cn.hutool.core.bean.BeanUtil;
import com.zjweu.context.BaseContext;
import com.zjweu.dto.TrainingRecordPageDTO;
import com.zjweu.po.User;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.service.TrainingRecordService;
import com.zjweu.vo.TraningRecordPageVO;
import com.zjweu.vo.UserPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
