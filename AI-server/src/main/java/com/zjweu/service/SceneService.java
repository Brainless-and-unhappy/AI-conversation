package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.dto.SceneDTO;
import com.zjweu.dto.ScenePageDTO;
import com.zjweu.po.Scene;
import com.zjweu.result.PageResult;
import com.zjweu.result.Result;
import com.zjweu.vo.SceneRecordsVO;
import com.zjweu.vo.SceneVO;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itcast
 * @since 2024-08-29
 */
public interface SceneService extends IService<Scene> {
    void addScene(SceneDTO scene);
    //Scene updateScene(Scene scene);
    Result deleteScene(Integer id);
    Scene getSceneById(Integer id);

    List<SceneRecordsVO> selectRecordsById();
    public List<SceneVO> getAllScenes();

    PageResult pagequery(ScenePageDTO scenePageDTO);

    void updateById1(SceneDTO sceneDTO);

    void delete(List<Integer> ids);
}
