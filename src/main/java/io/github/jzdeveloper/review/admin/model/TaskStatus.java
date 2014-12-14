package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 任务状态
 * @author mazhyb
 *
 */
@Entity
@Table(name="task_status")
public class TaskStatus extends AbstractModel<TaskStatus>{

	private static final long serialVersionUID = -2712344916530910288L;

	private String name;
	
	private String description;
	

	public String getName() {
		return name;
	}

	public TaskStatus setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public TaskStatus setDescription(String description) {
		this.description = description;
		return this;
	}

	private int orderBy;//排序
	
	public int getOrderBy() {
		return orderBy;
	}
	
	public TaskStatus setOrderBy(int orderBy) {
		this.orderBy = orderBy;
		return this;
	}
	
	
	
	
	
}
