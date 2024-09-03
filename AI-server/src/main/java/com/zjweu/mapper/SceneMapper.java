package com.zjweu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.zjweu.annotation.AutoFill;
import com.zjweu.enumeration.OperationType;
import com.zjweu.po.Scene;
import com.zjweu.vo.SceneRecordsVO;
import com.zjweu.vo.SceneVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Mapper
public interface SceneMapper extends BaseMapper<Scene> {
    @AutoFill(value = OperationType.INSERT)
    public void insertScene(Scene scene);
    public Scene getSceneById(int id);
    public void deleteSceneById(int id);

    SceneRecordsVO selectRecordsById(Integer userId, Integer id);
    public List<SceneVO> getAllScenes();

    Page<Scene> pagequery(Scene scene);

    @AutoFill(value = OperationType.UPDATE)
    void updateById1(Scene scene);
}
