package com.wangxuegang.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseRequest implements Serializable{

	private static final long serialVersionUID = -9205476782907620411L;
	
	private Integer pageNo;
	private Integer pageSize;
	
}
