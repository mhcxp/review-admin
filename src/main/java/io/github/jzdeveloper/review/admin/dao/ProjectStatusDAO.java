package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.ProjectStatus;

import org.hibernate.SessionFactory;


/**
 * @author mazhyb
 */
public class ProjectStatusDAO extends BaseDAO<ProjectStatus> {

	public ProjectStatusDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
