package com.zjweu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjweu.constant.MessageConstant;
import com.zjweu.context.BaseContext;
import com.zjweu.dto.SceneDTO;
import com.zjweu.dto.ScenePageDTO;
import com.zjweu.exception.BaseException;
import com.zjweu.mapper.SceneMapper;
import com.zjweu.po.Scene;
import com.zjweu.po.TrainingRecord;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.service.SceneService;
import com.zjweu.service.TrainingRecordService;
import com.zjweu.vo.SceneRecordsVO;
import com.zjweu.vo.SceneVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 *
 */
@Slf4j
@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {
    @Autowired
    private SceneMapper sceneMapper;

    @Autowired
    private TrainingRecordService trainingRecordService;
    public void addScene(SceneDTO sceneDTO){
        List<Scene> list = lambdaQuery().eq(sceneDTO.getName() != null, Scene::getName, sceneDTO.getName())
                .list();
        if (list.size() !=0 )
            throw new BaseException(MessageConstant.NAME_EXISTS);
        Scene scene =new Scene();
        BeanUtil.copyProperties(sceneDTO,scene);
        if(BeanUtil.isEmpty(scene.getPrompt()) || BeanUtil.isEmpty(scene.getName())){
            throw new BaseException(MessageConstant.IS_NULL);
        }
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
                recordsVO.setSceneName(scene.getName());
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

    @Override
    public PageResult pagequery(ScenePageDTO scenePageDTO) {
        PageHelper.startPage(scenePageDTO.getPage(),scenePageDTO.getPageSize());
        Scene scene = new Scene();
        scene.setName(scenePageDTO.getName());
        Page<Scene> scenes= baseMapper.pagequery(scene);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(scenes.getTotal());
        pageResult.setRecords(scenes.getResult());
        return pageResult;
    }

    @Override
    public void updateById1(SceneDTO sceneDTO) {
        List<Scene> list = lambdaQuery().eq(sceneDTO.getName() != null, Scene::getName, sceneDTO.getName())
                .ne(Scene::getId, sceneDTO.getId()).list();
        if(list.size()==0)
        {
            Scene scene = new Scene();
            BeanUtil.copyProperties(sceneDTO,scene);
            baseMapper.updateById1(scene);
        }else
            throw new BaseException(MessageConstant.NAME_EXISTS);
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        List<TrainingRecord> trainingRecordList = trainingRecordService.lambdaQuery().in(TrainingRecord::getSceneId, ids).list();
        trainingRecordService.delete(trainingRecordList);
        removeBatchByIds(ids);
    }
}
