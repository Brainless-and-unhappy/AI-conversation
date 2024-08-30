package com.zjweu.controller;


import com.zjweu.po.Scene;
import com.zjweu.result.Result;
import com.zjweu.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = "场景接口")
@RequestMapping("scene")
@RequiredArgsConstructor
public class SceneController {


    private final SceneService sceneService;
    @ApiOperation(value = "添加场景")
    @RequestMapping("/addScene")
    public Result addScene(@RequestBody Scene scene) {
        sceneService.addScene(scene);
        return Result.success();
    }
}
