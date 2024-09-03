package com.zjweu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.zjweu.dto.SceneDTO;
import com.zjweu.dto.ScenePageDTO;
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
    public Result addScene(@RequestBody SceneDTO sceneDTO) {
        log.info("添加场景{}",sceneDTO);
        sceneService.addScene(sceneDTO);
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
    public Result<PageResult> page(ScenePageDTO scenePageDTO){
        log.info("场景分页查询{}",scenePageDTO);
        PageResult pagequery = sceneService.pagequery(scenePageDTO);
        return Result.success(pagequery);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询场景信息")
    public Result<Scene> getById(@PathVariable  Integer id){
        log.info("根据id查询场景信息{}",id);
        Scene scene = sceneService.getById(id);
        return Result.success(scene);
    }
    @PostMapping("/update")
    @ApiOperation("修改场景信息")
    private Result update(@RequestBody SceneDTO sceneDTO){
        sceneService.updateById1(sceneDTO);

        return Result.success();
    }
    @DeleteMapping("")
    @ApiOperation("场景删除")
    public Result delectById(@RequestParam List<Integer> ids){
        log.info("场景删除{}",ids);

        sceneService.delete(ids);
        return Result.success();
    }

}

