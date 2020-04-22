package com.yodlee.support.microservice.dao.impl;

import javax.mail.Session;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yodlee.support.microservice.dao.DBDao;
import com.yodlee.support.microservice.dao.ResolutionDao;
import com.yodlee.support.microservice.entity.Issue;
import com.yodlee.support.microservice.entity.Resolution;
import com.yodlee.support.microservice.utility.Util;

@Repository
@Transactional
public class ResolutionDaoImpl implements ResolutionDao {

	@Autowired
	private DBDao dDao;

	@Autowired
	Session emailSession;

	@Override
	public Integer save(Resolution r) {
		int resoultionid = (Integer) dDao.getSession().save(r);
		Issue i = r.getIssue();

		if (dDao.isSendEmailEnabled()) {

			new Thread(() -> {
				String emailtemplate = Util.getEmailTemplate("user");
				emailtemplate = emailtemplate.replaceAll("#toolName", i.getToolInfo().getToolName())
						.replaceAll("#issueNumber", String.valueOf(i.getIssueId()))
						.replaceAll("#issuedescription", i.getIssueDescription())
						.replaceAll("#issuestatus", r.getResolvedBy()).replaceAll("#url", i.getToolInfo().getToolIp())
						.replaceAll("#reporterName", i.getReporterName()).replaceAll("#resolvedby", r.getResolvedBy());

				Util.emailNoAttachment(emailSession, i.getReporterName(),
						"issue status " + i.getStatusInfo().getStatusName(), emailtemplate);
			}).start();
		}

		return resoultionid;
	}

}
