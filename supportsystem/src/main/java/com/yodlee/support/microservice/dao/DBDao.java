package com.yodlee.support.microservice.dao;


import javax.transaction.Transactional;

import org.hibernate.Session;

@Transactional
public interface DBDao {

	public Session getSession();
	
	public boolean isSendEmailEnabled();

}
