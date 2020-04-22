package com.yodlee.support.microservice.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yodlee.support.microservice.dao.DummyDao;
import com.yodlee.support.microservice.entity.Dummy;

@Repository
@Transactional
public class DummyDaoImpl implements DummyDao {

	@Override
	public boolean printSession(Dummy dummy) {
		System.out.println(dummy);
		return false;
	}
	
	

}
