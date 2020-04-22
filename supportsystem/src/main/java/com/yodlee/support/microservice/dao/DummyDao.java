package com.yodlee.support.microservice.dao;


import javax.transaction.Transactional;

import com.yodlee.support.microservice.entity.Dummy;

@Transactional
public interface DummyDao {
	public boolean printSession(Dummy dummy);

}
