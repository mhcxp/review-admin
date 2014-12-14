package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 任务类型
 * @author mazhyb
 *
 */
@Entity
@Table(name="task_type")
public class TaskType extends AbstractModel<TaskType>{

	private static final long serialVersionUID = -6688137218910217457L;

	private String name;
	
	private String description;
	
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="types")
	private List<Task> tasks;

	public String getName() {
		return name;
	}

	public TaskType setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public TaskType setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public TaskType setTasks(List<Task> tasks) {
		this.tasks = tasks;
		return this;
	}

	private int orderBy;//排序
	
	public int getOrderBy() {
		return orderBy;
	}
	
	public TaskType setOrderBy(int orderBy) {
		this.orderBy = orderBy;
		return this;
	}
	
}
