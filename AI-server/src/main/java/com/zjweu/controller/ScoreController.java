package com.zjweu.controller;


import com.zjweu.dto.UserPageDTO;
import com.zjweu.dto.UserScorePageDTO;
import com.zjweu.po.Score;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("score")
@RequiredArgsConstructor
@Api(tags = "成绩接口")
public class ScoreController {

    private final ScoreService scoreService;
    @ApiOperation("用户分数分页查询")
    @PostMapping("/getScoreList")
    public Result<PageResult> page(UserScorePageDTO userScorePageDTO){
        //PageResult<Score> pageQuery=scoreService.getScoreList(userScorePageDTO);
        return Result.success();
    }
    @ApiOperation("详细成绩查询")
    @PostMapping("/getSubScore")
    public Result<List<Score>> getSubScore(Integer trainingId){
        List<Score> scoreList=scoreService.getSubScores(trainingId);
        return Result.success(scoreList);
    }
}
