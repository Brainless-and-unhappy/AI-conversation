package com.zjweu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zjweu.mapper.ScoreMapper;
import com.zjweu.po.Score;
import com.zjweu.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

}
