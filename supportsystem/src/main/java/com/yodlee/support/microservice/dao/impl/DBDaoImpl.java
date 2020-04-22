package com.yodlee.support.microservice.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import com.yodlee.support.microservice.dao.DBDao;

@Repository
@Transactional
public class DBDaoImpl implements DBDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Environment env;

	@Override
	public Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	@Override
	public boolean isSendEmailEnabled() {
		return Boolean.valueOf(env.getProperty("email.sendemail"));
	}

}
