package com.yodlee.support.microservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the tool_info database table.
 * 
 */
@Entity(name = "tool_info")

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ToolInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tool_id")
	private int toolId;

	@JsonIgnore
	@Column(name = "created_by")
	private String createdBy;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "row_created")
	private Date rowCreated;

	@Column(name = "tool_ip")
	private String toolIp;

	@Column(name = "tool_name")
	private String toolName;

	@OneToMany(mappedBy = "toolInfo")
	private List<Issue> issues;

	@Column(name = "email_contact")
	private String emailContact;

	private int port;
	
	public ToolInfo() {
	}

	public ToolInfo(int toolId) {
		this.toolId = toolId;
	}

	public int getToolId() {
		return this.toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getRowCreated() {
		return this.rowCreated;
	}

	public void setRowCreated(Date rowCreated) {
		this.rowCreated = rowCreated;
	}

	public String getToolIp() {
		return this.toolIp;
	}

	public void setToolIp(String toolIp) {
		this.toolIp = toolIp;
	}

	public String getToolName() {
		return this.toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}