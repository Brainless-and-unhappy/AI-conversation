package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjweu.po.Scene;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Mapper
public interface SceneMapper extends BaseMapper<Scene> {
    public void insertScene(Scene scene);
    public Scene getSceneById(int id);
    public void deleteSceneById(int id);

}
