package com.wangxuegang;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringbootQuartzDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootQuartzDemoApplication.class, args);
		log.info("springboot-quartz-demo 服务启动成功！！！");
	}
}