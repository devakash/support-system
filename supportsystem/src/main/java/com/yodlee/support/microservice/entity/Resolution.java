package com.yodlee.support.microservice.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the resolution database table.
 * 
 */
@Entity
@NamedQuery(name="Resolution.findAll", query="SELECT r FROM Resolution r")
public class Resolution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="resolution_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int resolutionId;

	@Lob
	@JsonIgnore
	private byte[] attachment;

	@Column(name="resolution_desc")
	private String resolutionDesc;

	@Column(name="resolved_by")
	private String resolvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="row_created",insertable=false)
	private Date rowCreated;

	@Column(name="was_bug")
	private int wasBug;

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="issue_id")
	private Issue issue;

	public Resolution() {
	}

	public int getResolutionId() {
		return this.resolutionId;
	}

	public void setResolutionId(int resolutionId) {
		this.resolutionId = resolutionId;
	}

	public byte[] getAttachment() {
		return this.attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getResolutionDesc() {
		return this.resolutionDesc;
	}

	public void setResolutionDesc(String resolutionDesc) {
		this.resolutionDesc = resolutionDesc;
	}

	public String getResolvedBy() {
		return this.resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public Date getRowCreated() {
		return this.rowCreated;
	}

	public void setRowCreated(Date rowCreated) {
		this.rowCreated = rowCreated;
	}

	public int getWasBug() {
		return this.wasBug;
	}

	public void setWasBug(int wasBug) {
		this.wasBug = wasBug;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

}