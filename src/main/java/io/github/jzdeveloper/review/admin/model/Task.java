package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name="task")
public class Task extends AbstractModel<Task>{

	private static final long serialVersionUID = 6790271204490741526L;

	@Column(length=100)
	private String title;
	
	@ManyToOne
	@JoinColumn(name="project_pid")
	private Project project;
	
//	@Column(length=255)
//	private String type;//任务类型,bug?feature?标签性质的 提供多种标签的组合，保存在数据库里使用的是;分隔保存

	@ManyToMany(cascade = { CascadeType.ALL },targetEntity=TaskType.class)
	@JoinTable(name = "task_type_a", 
		joinColumns = { @JoinColumn(name = "task_pid") }, 
		inverseJoinColumns = { @JoinColumn(name = "tasktype_pid") })
	@OrderBy("index")
	private List<TaskType> types;//任务类型
	
	@ManyToOne
	@JoinColumn(name="status_pid")
	private TaskStatus status;//状态
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User assignTo;
	
	@ManyToOne
	@JoinColumn(name="creator_id")
	private User creator;//创建人
	
	@Column(length=1000)
	private String description;//任务描述
	
	private Date start;//预计起始时间
	
	private Date end;//预计结束时间
	
	private Date created;//创建时间
	
	private Date finished;//实际完成时间

	public String getTitle() {
		return title;
	}

	public Task setTitle(String title) {
		this.title = title;
		return this;
	}

	public Project getProject() {
		return project;
	}

	public Task setProject(Project project) {
		this.project = project;
		return this;
	}

	public List<TaskType> getTypes() {
		return types;
	}
	
	public Task setTypes(List<TaskType> types) {
		this.types = types;
		return this;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public Task setStatus(TaskStatus status) {
		this.status = status;
		return this;
	}

	public User getAssignTo() {
		return assignTo;
	}

	public Task setAssignTo(User assignTo) {
		this.assignTo = assignTo;
		return this;
	}

	public User getCreator() {
		return creator;
	}

	public Task setCreator(User creator) {
		this.creator = creator;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Task setDescription(String description) {
		this.description = description;
		return this;
	}

	public Date getStart() {
		return start;
	}

	public Task setStart(Date start) {
		this.start = start;
		return this;
	}

	public Date getEnd() {
		return end;
	}

	public Task setEnd(Date end) {
		this.end = end;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public Task setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getFinished() {
		return finished;
	}

	public Task setFinished(Date finished) {
		this.finished = finished;
		return this;
	}
	
	
	
	
	
	
	
}
