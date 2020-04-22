package com.yodlee.support.microservice.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yodlee.support.microservice.dao.DBDao;
import com.yodlee.support.microservice.dao.IssueDao;
import com.yodlee.support.microservice.entity.Issue;
import com.yodlee.support.microservice.entity.IssueStatusInfo;
import com.yodlee.support.microservice.entity.ToolInfo;
import com.yodlee.support.microservice.utility.Util;

@Repository
@Transactional
public class IssueDaoImpl implements IssueDao {

	@Autowired
	private DBDao dDao;

	

	@Override
	public Integer createIssue(Issue i) {
		IssueStatusInfo statusInfo = getStatusInfo(100001);
		i.setStatusInfo(statusInfo);
		Integer issueID = (Integer) dDao.getSession().save(i);

		String emailtemplate = Util.getEmailTemplate("admin");
		emailtemplate = emailtemplate.replaceAll("#toolName", i.getToolInfo().getToolName())
				.replaceAll("#issueNumber", String.valueOf(i.getIssueId()))
				.replaceAll("#issuedescription", i.getIssueDescription()).replaceAll("#raisedby", i.getReporterName()).replaceAll("#url", i.getToolInfo().getToolIp());

		return issueID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getIssues(ToolInfo toolinfo, String userName) {
		if (userName.trim().equalsIgnoreCase("ALL")) {
			return (List<Issue>) dDao.getSession().createQuery("from Issue where toolInfo.toolId=:toolid")
					.setParameter("toolid", toolinfo.getToolId()).list();
		} else {
			return (List<Issue>) dDao.getSession()
					.createQuery("from Issue where toolInfo.toolId=:toolid and reporterName=:userName ")
					.setParameter("toolid", toolinfo.getToolId()).setParameter("userName", userName).list();
		}
	}

	@Override
	public Issue getIssue(Integer issueid) {
		return (Issue) dDao.getSession().get(Issue.class, issueid);
	}

	@Override
	public void saveOrUpdate(Issue i) {
		dDao.getSession().update(i);
	}

	@Override
	public IssueStatusInfo getStatusInfo(Integer id) {
		return dDao.getSession().get(IssueStatusInfo.class, id);
	}

	@Override
	public List<IssueStatusInfo> getIssueStatus() {
		return dDao.getSession().createQuery("from IssueStatusInfo", IssueStatusInfo.class).list();
	}

}
