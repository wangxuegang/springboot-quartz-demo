package com.wangxuegang.service;

import org.quartz.SchedulerException;

import com.wangxuegang.entity.QuartzEntity;
import com.wangxuegang.entity.Result;

public interface JobService {

    Result listQuartzEntity(QuartzEntity quartz, Integer pageNo, Integer pageSize) throws SchedulerException;
    
    Long listQuartzEntity(QuartzEntity quartz);

    Result save(QuartzEntity quartz) throws Exception;
}
