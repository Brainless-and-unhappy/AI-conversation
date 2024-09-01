package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjweu.annotation.AutoFill;
import com.zjweu.enumeration.OperationType;
import com.zjweu.po.Scene;
import com.zjweu.vo.SceneRecordsVO;
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
    @AutoFill(value = OperationType.UPDATE)
    public void insertScene(Scene scene);
    public Scene getSceneById(int id);
    public void deleteSceneById(int id);

    SceneRecordsVO selectRecordsById(Integer userId, Integer id);
}
