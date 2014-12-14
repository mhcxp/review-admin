package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author mazhyb
 */
@Entity
@Table(name ="project")
public class Project extends AbstractModel<Project>{

	private static final long serialVersionUID = 3616329390858891184L;

	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="user_pid")
	private User leader;
	
	private Date start;
	
	private Date end;
	
	private Date creationTime;//创建时间
	
	private Date finishTime;//实际完成时间
	
	//项目状态
	@ManyToOne
	@JoinColumn(name="status_pid")
	private ProjectStatus status;

	public String getName() {
		return name;
	}

	public Project setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Project setDescription(String description) {
		this.description = description;
		return this;
	}

	public User getLeader() {
		return leader;
	}

	public Project setLeader(User leader) {
		this.leader = leader;
		return this;
	}

	public Date getStart() {
		return start;
	}

	public Project setStart(Date start) {
		this.start = start;
		return this;
	}

	public Date getEnd() {
		return end;
	}

	public Project setEnd(Date end) {
		this.end = end;
		return this;
	}

	
	public ProjectStatus getStatus() {
		return status;
	}
	
	public Project setStatus(ProjectStatus status) {
		this.status = status;
		return this;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public Project setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
		return this;
	}
	
	public Date getFinishTime() {
		return finishTime;
	}
	
	public Project setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
		return this;
	}
	
	
}
