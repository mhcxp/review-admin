package io.github.jzdeveloper.review.admin.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

@Entity
@Table(name="role")
public class Role extends AbstractModel<Role> {

	private static final long serialVersionUID = -1524171375399697583L;

	private String name;
	
	private String description;

	public String getName() {
		return name;
	}

	public Role setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Role setDescription(String description) {
		this.description = description;
		return this;
	}
	
	private int orderBy;//排序
	
	public int getOrderBy() {
		return orderBy;
	}
	
	public Role setOrderBy(int orderBy) {
		this.orderBy = orderBy;
		return this;
	}
	
	
}
