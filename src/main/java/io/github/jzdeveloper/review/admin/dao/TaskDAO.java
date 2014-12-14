package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.Task;

import org.hibernate.SessionFactory;


/**
 * @author mazhyb
 */
public class TaskDAO extends BaseDAO<Task> {

	public TaskDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
