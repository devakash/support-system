package com.yodlee.support.microservice.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the issue database table.
 * 
 */
@Entity(name = "issue")
public class Issue implements Serializable {
	public Issue(String issueDescription, IssueStatusInfo statusInfo, String issueType, String reporterName,
			ToolInfo toolInfo) {
		super();
		this.issueDescription = issueDescription;
		this.statusInfo = statusInfo;
		this.issueType = issueType;
		this.reporterName = reporterName;
		this.toolInfo = toolInfo;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "issue_id")
	private int issueId;

	@Column(name = "issue_description")
	private String issueDescription;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private IssueStatusInfo statusInfo;

	@Column(name = "issue_type")
	private String issueType;

	@Column(name = "reporter_name")
	private String reporterName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "row_created", length = 19, insertable = false)
	private Date rowCreated;

	@JsonIgnore
	@OneToMany(mappedBy = "issue")
	private List<Attachment> attachments;

	// bi-directional many-to-one association to ToolInfo
	@ManyToOne
	@JoinColumn(name = "tool_id")
	private ToolInfo toolInfo;

	// bi-directional many-to-one association to Resolution
	@OneToMany(mappedBy = "issue",fetch=FetchType.EAGER)
	private List<Resolution> resolutions;

	public Issue() {
	}

	public int getIssueId() {
		return this.issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssueDescription() {
		return this.issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getIssueType() {
		return this.issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getReporterName() {
		return this.reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public Date getRowCreated() {
		return this.rowCreated;
	}

	public void setRowCreated(Date rowCreated) {
		this.rowCreated = rowCreated;
	}

	public List<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Attachment addAttachment(Attachment attachment) {
		getAttachments().add(attachment);
		attachment.setIssue(this);

		return attachment;
	}

	public Attachment removeAttachment(Attachment attachment) {
		getAttachments().remove(attachment);
		attachment.setIssue(null);

		return attachment;
	}

	public ToolInfo getToolInfo() {
		return this.toolInfo;
	}

	public void setToolInfo(ToolInfo toolInfo) {
		this.toolInfo = toolInfo;
	}

	public List<Resolution> getResolutions() {
		return this.resolutions;
	}

	public void setResolutions(List<Resolution> resolutions) {
		this.resolutions = resolutions;
	}

	public Resolution addResolution(Resolution resolution) {
		getResolutions().add(resolution);
		resolution.setIssue(this);

		return resolution;
	}

	public Resolution removeResolution(Resolution resolution) {
		getResolutions().remove(resolution);
		resolution.setIssue(null);

		return resolution;
	}

	public IssueStatusInfo getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(IssueStatusInfo statusInfo) {
		this.statusInfo = statusInfo;
	}

}