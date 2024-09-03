package com.zjweu.controller;


import com.zjweu.dto.UserPageDTO;
import com.zjweu.po.Scene;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.service.SceneService;
import com.zjweu.vo.SceneRecordsVO;
import com.zjweu.vo.SceneVO;
import com.zjweu.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "场景接口")
@RequestMapping("/admin/scene")
@RequiredArgsConstructor
public class SceneController {


    private final SceneService sceneService;
    @ApiOperation(value = "添加场景")
    @PostMapping("/addScene")
    public Result addScene(@RequestBody Scene scene) {
        log.info("添加场景{}",scene);
        sceneService.addScene(scene);
        return Result.success();
    }

    @ApiOperation(value = "查询场景及成绩")
    @GetMapping("/selectRecordsById")
    public Result selectRecordsById(){
        List<SceneRecordsVO> sceneRecordsVOS= sceneService.selectRecordsById();
        return Result.success(sceneRecordsVOS);

    }
    @ApiOperation("返回所有场景")
    @GetMapping("/getAllScene")
    public Result getAllScene(){
        List<Scene> list = sceneService.list();
        return Result.success(list);
    }
    @GetMapping("/page")
    @ApiOperation("场景分页查询")
    public Result<PageResult> page(UserPageDTO userPageDTO){
        log.info("场景分页查询{}",userPageDTO);
        PageResult pagequery = sceneService.pagequery(userPageDTO);
        return Result.success(pagequery);
    }
}
