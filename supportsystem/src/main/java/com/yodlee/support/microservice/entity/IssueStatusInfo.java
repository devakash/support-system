package com.yodlee.support.microservice.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the tool_info database table.
 * 
 */
@Entity
@Table(name = "issue_status_info")
@NamedQuery(name = "IssueStatusInfo.findAll", query = "SELECT t FROM IssueStatusInfo t")
public class IssueStatusInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public IssueStatusInfo() {

	}

	public IssueStatusInfo(Integer statusId) {
		this.statusId = statusId;
	}

	@Id
	@Column(name = "status_id")
	private int statusId;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "row_created")
	private Date rowCreated;

	@Column(name = "status_name")
	private String statusName;

	public IssueStatusInfo(int statusId) {
		this.statusId = statusId;
	}

	public Date getRowCreated() {
		return this.rowCreated;
	}

	public void setRowCreated(Date rowCreated) {
		this.rowCreated = rowCreated;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}