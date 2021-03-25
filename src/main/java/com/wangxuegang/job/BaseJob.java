package com.wangxuegang.job;

import java.io.Serializable;

import lombok.Getter;

import org.quartz.Job;

@Getter
public abstract class BaseJob implements Job,Serializable{

	private static final long serialVersionUID = 4546976448234160694L;
	
	private String jobName = "";//任务名称
    private String jobGroup = "";//任务分组
    private String jobClassName = "";//执行类
    private String cronExpression = "";//执行时间
    private String description = "";//任务描述
}
