package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mazhyb
 */
@Entity
@Table(name = "review")
@XmlRootElement
public class Review extends AbstractModel<Review> {

	private static final long serialVersionUID = 749207907627655920L;

	public Review() {

	}

	public Review(long pid, String id) {
		this.pid = pid;
		this.id = id;
	}


	@Column(unique = true, length = 50)
	private String id;

	private String description;
	@Column(length = 50)
	private String author;

	private Date creationDate;

	private String directory;

	@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	@JoinTable(name = "review_reviewer", joinColumns = { @JoinColumn(name = "review_pid") }, inverseJoinColumns = { @JoinColumn(name = "reviewer_pid") })
	@OrderBy("pid")
	private List<Reviewer> reviewers;

	@OneToMany(fetch = FetchType.EAGER,orphanRemoval=true)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="review_pid",referencedColumnName="pid")
	private List<File> files;

	@OneToMany(fetch = FetchType.EAGER,orphanRemoval=true)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="review_pid",referencedColumnName="pid")
	private List<FieldItem> fieldItems;

	@OneToMany(fetch = FetchType.EAGER,orphanRemoval=true)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="review_pid",referencedColumnName="pid")
	private List<Phase> filters;

	@OneToMany(fetch = FetchType.EAGER,orphanRemoval=true)
	//(cascade = { CascadeType.ALL },fetch = FetchType.EAGER,mappedBy="review",orphanRemoval=true)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="review_pid",referencedColumnName="pid")
	private List<Issue> issues;

	/**
	 * 是否关闭
	 */
	private boolean isClose;


	public String getId() {
		return id;
	}

	public Review setId(String id) {
		this.id = id;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Review setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Review setAuthor(String author) {
		this.author = author;
		return this;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Review setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public String getDirectory() {
		return directory;
	}

	public Review setDirectory(String directory) {
		this.directory = directory;
		return this;
	}

	public List<Reviewer> getReviewers() {
		return reviewers;
	}

	public Review setReviewers(List<Reviewer> reviewers) {
		this.reviewers = reviewers;
		return this;
	}

	public List<File> getFiles() {
		return files;
	}

	public Review setFiles(List<File> files) {
		this.files = files;
		return this;
	}

	public List<FieldItem> getFieldItems() {
		return fieldItems;
	}

	public Review setFieldItems(List<FieldItem> fieldItems) {
		this.fieldItems = fieldItems;
		return this;
	}

	public List<Phase> getFilters() {
		return filters;
	}

	public Review setFilters(List<Phase> filters) {
		this.filters = filters;
		return this;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public Review setIssues(List<Issue> issues) {
		this.issues = issues;
		return this;
	}

	public boolean isClose() {
		return isClose;
	}

	public Review setClose(boolean isClose) {
		this.isClose = isClose;
		return this;
	}

}
