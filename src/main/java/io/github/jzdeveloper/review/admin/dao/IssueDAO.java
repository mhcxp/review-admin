package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.Issue;

import org.hibernate.SessionFactory;

public class IssueDAO extends BaseDAO<Issue>{

	public IssueDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

}
