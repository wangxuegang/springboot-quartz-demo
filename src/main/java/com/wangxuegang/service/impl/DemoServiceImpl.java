package com.wangxuegang.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.wangxuegang.service.DemoService;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

	@Override
	public void info() {
		log.info("业务处理中！！！");
	}

}
