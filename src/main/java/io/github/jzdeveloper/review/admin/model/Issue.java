package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "issue")
public class Issue extends AbstractModel<Issue> {

	private static final long serialVersionUID = -2673578530352638170L;

	private String id;

	private Date creationDate;

	private Date lastModificationDate;

	private String reviewerId;
	private String assignedTo;

	private String fileName;

	private String fileLine;

	private String severity;
	private String summary;

	private String description;

	private String annotation;
	private String revision;
	private String resolution;
	private String status;

	private String type;
	
	public String getType() {
		return type;
	}
	
	public Issue setType(String type) {
		this.type = type;
		return this;
	}
	
	public String getId() {
		return id;
	}

	public Issue setId(String id) {
		this.id = id;
		return this;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Issue setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public Issue setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
		return this;
	}

	public String getReviewerId() {
		return reviewerId;
	}

	public Issue setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
		return this;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public Issue setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public Issue setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public String getFileLine() {
		return fileLine;
	}

	public Issue setFileLine(String fileLine) {
		this.fileLine = fileLine;
		return this;
	}

	public String getSeverity() {
		return severity;
	}

	public Issue setSeverity(String severity) {
		this.severity = severity;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public Issue setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Issue setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getAnnotation() {
		return annotation;
	}

	public Issue setAnnotation(String annotation) {
		this.annotation = annotation;
		return this;
	}

	public String getRevision() {
		return revision;
	}

	public Issue setRevision(String revision) {
		this.revision = revision;
		return this;
	}

	public String getResolution() {
		return resolution;
	}

	public Issue setResolution(String resolution) {
		this.resolution = resolution;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Issue setStatus(String status) {
		this.status = status;
		return this;
	}

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name = "review_pid",referencedColumnName="pid",insertable=true,updatable=true,foreignKey=@ForeignKey(name="review_pid"))
//	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Review review;

	public Review getReview() {
		return review;
	}

	public Issue setReview(Review review) {
		this.review = review;
		return this;
	}
	
	//根据状态判断是否为关闭状态
	public boolean isClosed(){
		return TYPE_CLOSE.equals(status);
	}
	
	public void close(){
		this.status = TYPE_CLOSE;
	}
	
	public static final String TYPE_CLOSE = "item.status.label.closed";

}
