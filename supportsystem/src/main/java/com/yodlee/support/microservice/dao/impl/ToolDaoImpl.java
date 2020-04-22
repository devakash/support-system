package com.yodlee.support.microservice.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yodlee.support.microservice.dao.DBDao;
import com.yodlee.support.microservice.dao.ToolDao;
import com.yodlee.support.microservice.entity.ToolInfo;

@Repository
@Transactional
public class ToolDaoImpl implements ToolDao{

	@Autowired
	private DBDao dDao;

	@Override
	public ToolInfo getToolInfo(int id) {
		return dDao.getSession().get(ToolInfo.class, id);
	}
	
	

	

}
