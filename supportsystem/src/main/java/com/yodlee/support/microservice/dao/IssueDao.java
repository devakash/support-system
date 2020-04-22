package com.yodlee.support.microservice.dao;

import java.util.List;

import com.yodlee.support.microservice.entity.Issue;
import com.yodlee.support.microservice.entity.IssueStatusInfo;
import com.yodlee.support.microservice.entity.ToolInfo;

public interface IssueDao {

	public Integer createIssue(Issue issue);

	public List<Issue> getIssues(ToolInfo toolinfo, String userName);

	public Issue getIssue(Integer issueid);

	public void saveOrUpdate(Issue i);

	public IssueStatusInfo getStatusInfo(Integer id);

	public List<IssueStatusInfo> getIssueStatus();

}
