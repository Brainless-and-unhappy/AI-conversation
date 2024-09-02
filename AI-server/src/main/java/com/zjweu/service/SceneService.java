package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.po.Scene;
import com.zjweu.result.Result;
import com.zjweu.vo.SceneRecordsVO;

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
    void addScene(Scene scene);
    //Scene updateScene(Scene scene);
    Result deleteScene(Integer id);
    Scene getSceneById(Integer id);

    List<SceneRecordsVO> selectRecordsById();
}
