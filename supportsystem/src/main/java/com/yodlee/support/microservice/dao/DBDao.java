package com.yodlee.support.microservice.dao;

import org.hibernate.Session;

public interface DBDao {

	public Session getSession();

	public boolean isSendEmailEnabled();

}
