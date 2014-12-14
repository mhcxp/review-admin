package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 项目状态表
 * @author mazhyb
 */
@Entity
@Table(name="project_status")
public class ProjectStatus extends AbstractModel<ProjectStatus>{

	private static final long serialVersionUID = -3036671234605388239L;

	/**
	 * 显示名称
	 */
	private String name;
	
	
	
	/**
	 * 说明
	 */
	private String description;

	public String getName() {
		return name;
	}

	public ProjectStatus setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ProjectStatus setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	private int orderBy;//排序
	
	public int getOrderBy() {
		return orderBy;
	}
	
	public ProjectStatus setOrderBy(int orderBy) {
		this.orderBy = orderBy;
		return this;
	}
	
	
	
}
