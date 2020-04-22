package com.yodlee.support.microservice.dao;

import javax.transaction.Transactional;

import com.yodlee.support.microservice.entity.Attachment;

@Transactional
public interface AttachmentDao {
	
	public Integer save(Attachment at);
	
	public byte[] getAttachment(Integer id);
}
