package com.yodlee.support.microservice.dao;

import javax.transaction.Transactional;

import com.yodlee.support.microservice.entity.Resolution;

@Transactional
public interface ResolutionDao {
	
	public Integer save(Resolution r);
}
