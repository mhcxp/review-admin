package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.Project;

import org.hibernate.SessionFactory;


/**
 * @author mazhyb
 */
public class ProjectDAO extends BaseDAO<Project> {

	public ProjectDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
