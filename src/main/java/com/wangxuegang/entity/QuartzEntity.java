package com.wangxuegang.entity;

import lombok.Data;

@Data
public class QuartzEntity extends BaseRequest{

    private String jobName;//任务名称
    private String jobGroup;//任务分组
    private String jobClassName;//执行类
    private String cronExpression;//执行时间
    private String description;//任务描述

    private String triggerName; //触发名称
    private String triggerState; //触发状态
    
    private String oldJobName;//任务名称 用于修改
    private String oldJobGroup;//任务分组 用于修改
}
