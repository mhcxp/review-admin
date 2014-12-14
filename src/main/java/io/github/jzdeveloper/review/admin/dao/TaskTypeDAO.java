package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.TaskType;

import org.hibernate.SessionFactory;


/**
 * @author mazhyb
 */
public class TaskTypeDAO extends BaseDAO<TaskType> {

	public TaskTypeDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
