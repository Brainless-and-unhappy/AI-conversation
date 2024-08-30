package com.zjweu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zjweu.mapper.SceneMapper;
import com.zjweu.po.Scene;
import com.zjweu.result.Result;
import com.zjweu.service.SceneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {
    @Autowired
    private SceneMapper sceneMapper;
    public void addScene(Scene scene){
        sceneMapper.insertScene(scene);
    }
    public Result deleteScene(Integer id){
        if(getSceneById(id)==null){
            return Result.error("该场景不存在");
        }
        else {
            sceneMapper.deleteSceneById(id);
            return Result.success();
        }
    }
    public Scene getSceneById(int id) {
        Scene scene = getSceneById(id);
        return scene;
    }
}
