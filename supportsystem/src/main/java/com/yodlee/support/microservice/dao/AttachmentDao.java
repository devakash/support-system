package com.yodlee.support.microservice.dao;

import com.yodlee.support.microservice.entity.Attachment;

public interface AttachmentDao {
	
	public Integer save(Attachment at);
	
	public byte[] getAttachment(Integer id);
}
