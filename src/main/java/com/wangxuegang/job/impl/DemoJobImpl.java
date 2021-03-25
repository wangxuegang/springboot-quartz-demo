package com.wangxuegang.job.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.wangxuegang.job.BaseJob;
import com.wangxuegang.service.DemoService;
import com.wangxuegang.util.SpringUtil;

@Getter
@DisallowConcurrentExecution
@Component
@Slf4j
public class DemoJobImpl extends BaseJob{

	private static final long serialVersionUID = -8402598407207447739L;
	
	private String jobName = "demo"; //任务名称
	private String jobGroup = "demo"; //任务组
    private String jobClassName = "com.wangxuegang.job.impl.DemoJobImpl"; //类全名
    private String cronExpression = "*/5 * * * * ?"; //cron表达式
    private String description = "demo";//任务描述
    
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		log.info("demo 任务执行开始！！！");
		DemoService demoService = SpringUtil.getBean(DemoService.class);
		demoService.info();
		log.info("demo 任务执行结束！！！");
		
	}

}
