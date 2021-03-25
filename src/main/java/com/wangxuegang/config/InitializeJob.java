package com.wangxuegang.config;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.wangxuegang.entity.QuartzEntity;
import com.wangxuegang.job.BaseJob;
import com.wangxuegang.service.JobService;
import com.wangxuegang.util.ClassScaner;
import com.wangxuegang.util.SpringUtil;

@Component
@Slf4j
public class InitializeJob implements ApplicationRunner {

	@Autowired
	private JobService jobService;
	@Autowired
	private Scheduler scheduler;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Set<Class> scan = ClassScaner.scan("com.wangxuegang.job.impl", null);
		for (Class aclass : scan) {
			Object bean = SpringUtil.getBean(aclass);
			BaseJob jobBean = (BaseJob) bean;
			QuartzEntity quartzEntity = new QuartzEntity();
			BeanUtils.copyProperties(jobBean, quartzEntity);
			
			if(scheduler.checkExists(JobKey.jobKey(quartzEntity.getJobName(), quartzEntity.getJobGroup()))){
				log.info(quartzEntity.getJobName()+":job已存在，无需初始化任务！！！");
			}else{
				add(quartzEntity);
			}
		}
	}

	private void add(QuartzEntity quartzEntity) throws Exception {
		
		Class jobClass = Class.forName(quartzEntity.getJobClassName()) ;
		
		if (jobClass == null) {
            log.error("msg", "类不存在,您的类是：" + quartzEntity.getJobClassName());
        }
		
		jobClass.newInstance();
        //构建job信息
        JobDetail job = JobBuilder.newJob(jobClass).withIdentity(quartzEntity.getJobName(),
        		quartzEntity.getJobGroup())
                .withDescription(quartzEntity.getDescription()).build();
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzEntity.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+quartzEntity.getJobName(), quartzEntity.getJobGroup())
                .startNow().withSchedule(cronScheduleBuilder).build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);
		
	}

}
