package com.wangxuegang.controller;


import lombok.extern.slf4j.Slf4j;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangxuegang.entity.QuartzEntity;
import com.wangxuegang.entity.Result;
import com.wangxuegang.service.JobService;

@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

	@Autowired
    private Scheduler scheduler;
    @Autowired
    private JobService jobService;
    
	@PostMapping("/add")
	public Result save(@RequestBody QuartzEntity request){
		log.info("新增任务");
        try {
        	return jobService.save(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }
	}
	@PostMapping("/list")
	public Result list(@RequestBody QuartzEntity request) throws SchedulerException {
		log.info("任务列表");
        return jobService.listQuartzEntity(request, request.getPageNo(), request.getPageSize());
	}
	@PostMapping("/trigger")
	public  Result trigger(@RequestBody QuartzEntity request) {
		log.info("触发任务");
		try {
		     JobKey key = new JobKey(request.getJobName(),request.getJobGroup());
		     scheduler.triggerJob(key);
		} catch (SchedulerException e) {
			 log.error(e.getMessage());
			 return Result.error();
		}
		return Result.ok();
	}
	@PostMapping("/pause")
	public  Result pause(@RequestBody QuartzEntity request) {
		log.info("停止任务");
		try {
		     JobKey key = new JobKey(request.getJobName(),request.getJobGroup());
		     scheduler.pauseJob(key);
		} catch (SchedulerException e) {
			 log.error(e.getMessage());
			 return Result.error();
		}
		return Result.ok();
	}
	@PostMapping("/resume")
	public  Result resume(@RequestBody QuartzEntity request) {
		log.info("恢复任务");
		try {
		     JobKey key = new JobKey(request.getJobName(),request.getJobGroup());
		     scheduler.resumeJob(key);
		} catch (SchedulerException e) {
			 log.error(e.getMessage());
			 return Result.error();
		}
		return Result.ok();
	}
	@PostMapping("/remove")
	public  Result remove(@RequestBody QuartzEntity request) {
		log.info("移除任务");
		try {  
            TriggerKey triggerKey = TriggerKey.triggerKey(request.getJobName(), request.getJobGroup());  
            // 停止触发器  
            scheduler.pauseTrigger(triggerKey);  
            // 移除触发器  
            scheduler.unscheduleJob(triggerKey);  
            // 删除任务  
            scheduler.deleteJob(JobKey.jobKey(request.getJobName(), request.getJobGroup()));  
            System.out.println("removeJob:"+JobKey.jobKey(request.getJobName()));  
        } catch (Exception e) {  
        	log.error(e.getMessage());
            return Result.error();
        }  
		return Result.ok();
	}
}
