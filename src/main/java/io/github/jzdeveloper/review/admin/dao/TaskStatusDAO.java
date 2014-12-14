package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.TaskStatus;

import org.hibernate.SessionFactory;


/**
 * @author mazhyb
 */
public class TaskStatusDAO extends BaseDAO<TaskStatus> {

	public TaskStatusDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
