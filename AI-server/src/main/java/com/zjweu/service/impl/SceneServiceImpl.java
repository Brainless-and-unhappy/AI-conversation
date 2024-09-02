package com.zjweu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.zjweu.context.BaseContext;
import com.zjweu.mapper.SceneMapper;
import com.zjweu.po.Scene;
import com.zjweu.result.Result;
import com.zjweu.service.SceneService;
import com.zjweu.vo.SceneRecordsVO;
import com.zjweu.vo.SceneVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {
    @Autowired
    private SceneMapper sceneMapper;
    public void addScene(Scene scene){
        if(scene.getPrompt().toString()!="" && scene.getName().toString()!=""){
            scene.setOperator(BaseContext.getCurrentId());
            scene.setUpdateDate(LocalDateTime.now());
            sceneMapper.insertScene(scene);
        }

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
    public Scene getSceneById(Integer id) {
        Scene scene = sceneMapper.getSceneById(id);
        return scene;
    }

    @Override
    public List<SceneRecordsVO> selectRecordsById() {
        Integer userId = BaseContext.getCurrentId();
        List<Scene> scenes = list();
        List<SceneRecordsVO> sceneRecordsVOS =new ArrayList<>();
        for (Scene scene:scenes) {
            SceneRecordsVO recordsVO= sceneMapper.selectRecordsById(userId,scene.getId());
            if(BeanUtil.isEmpty(recordsVO)){
                recordsVO=new SceneRecordsVO();
                recordsVO.setId(scene.getId());
                recordsVO.setSeceneName(scene.getName());
                recordsVO.setHighestScore(0);
                recordsVO.setTrainingCount(0);
            }
            sceneRecordsVOS.add(recordsVO);
        }
        return sceneRecordsVOS;


    }
    @Override
    public List<SceneVO> getAllScenes(){
        return sceneMapper.getAllScenes();
    }
}
