package com.zjweu.controller;


import com.zjweu.service.TrainingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itcast
 */
@Slf4j
@RestController
@RequestMapping("trainingRecord")
public class TrainingRecordController {

    @Autowired
    private TrainingRecordService trainingRecordService;
}
