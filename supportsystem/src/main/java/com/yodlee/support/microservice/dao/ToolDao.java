package com.yodlee.support.microservice.dao;

import javax.transaction.Transactional;

import com.yodlee.support.microservice.entity.ToolInfo;

@Transactional
public interface ToolDao {
	
	public ToolInfo getToolInfo(int id);
}
