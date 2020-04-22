package com.yodlee.support.microservice.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yodlee.support.microservice.dao.AttachmentDao;
import com.yodlee.support.microservice.dao.DBDao;
import com.yodlee.support.microservice.entity.Attachment;

@Repository
@Transactional
public class AttachmentDaoImpl implements AttachmentDao{

	@Autowired
	private DBDao dDao;

	@Override
	public Integer save(Attachment at) {
		return (Integer)dDao.getSession().save(at);
	}

	@Override
	public byte[] getAttachment(Integer id) {
		Attachment a=(Attachment)dDao.getSession().createQuery("from Attachment where issue.issueId=:id").setParameter("id", id).uniqueResult();
		return a==null?null:a.getAttachmentContent();
	}

	
	
	

	

}
