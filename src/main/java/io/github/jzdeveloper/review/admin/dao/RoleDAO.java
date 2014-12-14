package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.Role;

import org.hibernate.SessionFactory;


/**
 * 角色相关操作
 * @author mazhyb
 */
public class RoleDAO extends BaseDAO<Role> {

	public RoleDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
