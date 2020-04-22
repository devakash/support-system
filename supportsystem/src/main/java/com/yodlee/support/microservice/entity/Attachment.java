package com.yodlee.support.microservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Attachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="attachment_id")
	private int attachmentId;

	@Lob
	@Column(name="attachment_content")
	private byte[] attachmentContent;

	@Column(name="attachment_type")
	private int attachmentType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "row_created", length = 19, insertable = false)
	private Date rowCreated;

	//bi-directional many-to-one association to Issue
	@ManyToOne
	@JoinColumn(name="issue_id")
	private Issue issue;

	public Attachment() {
	}

	public int getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public byte[] getAttachmentContent() {
		return this.attachmentContent;
	}

	public void setAttachmentContent(byte[] attachmentContent) {
		this.attachmentContent = attachmentContent;
	}

	public int getAttachmentType() {
		return this.attachmentType;
	}

	public void setAttachmentType(int attachmentType) {
		this.attachmentType = attachmentType;
	}

	public Date getRowCreated() {
		return this.rowCreated;
	}

	public void setRowCreated(Date rowCreated) {
		this.rowCreated = rowCreated;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

}