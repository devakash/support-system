package com.yodlee.support.microservice.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yodlee.support.microservice.dao.AttachmentDao;
import com.yodlee.support.microservice.dao.IssueDao;
import com.yodlee.support.microservice.dao.ResolutionDao;
import com.yodlee.support.microservice.dao.ToolDao;
import com.yodlee.support.microservice.entity.Attachment;
import com.yodlee.support.microservice.entity.Issue;
import com.yodlee.support.microservice.entity.IssueStatusInfo;
import com.yodlee.support.microservice.entity.Resolution;
import com.yodlee.support.microservice.entity.ToolInfo;

@Service("iservice")
@Transactional
public class IssueService {

	@Autowired
	IssueDao iDao;

	@Autowired
	ToolDao tDao;

	@Autowired
	AttachmentDao aDao;

	@Autowired
	ResolutionDao rDao;

	public Integer createIssue(Issue issue, MultipartFile file) throws IOException {

		Integer createIssue = iDao.createIssue(issue);
		if (file != null) {
			Attachment a = new Attachment();
			a.setIssue(issue);
			a.setAttachmentType(1);
			a.setAttachmentContent(file.getBytes());
			aDao.save(a);
		}
		return createIssue;
	}

	public List<Issue> getAllIssues(ToolInfo toolInfo, String userName) {
		return iDao.getIssues(toolInfo, userName);
	}

	public byte[] getImage(Integer issueid) {
		return aDao.getAttachment(issueid);
	}

	public Integer updateResolution(Resolution r) {

		return rDao.save(r);

	}

	public List<IssueStatusInfo> getIssueStatus() {
		return iDao.getIssueStatus();
	}
}
