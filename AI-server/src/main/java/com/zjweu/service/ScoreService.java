package com.zjweu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjweu.dto.UserScorePageDTO;
import com.zjweu.po.Score;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itcast
 * @since 2024-08-29
 */
public interface ScoreService extends IService<Score> {
    public List<Score> getScoreList(UserScorePageDTO userScorePageDTO);
    public Score getSubScores(Integer trainingId);
}
