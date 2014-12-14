package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.User;

import org.hibernate.Query;
import org.hibernate.SessionFactory;


/**
 * @author mazhyb
 */
public class UserDAO extends BaseDAO<User> {

	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	/**
	 * 从数据库中查询是否存在相同名称的用户
	 * @param name
	 * @return
	 */
	public boolean hasSameName(String name){
		Query query = currentSession().createQuery("select count(*) from User where name=:name");
		query.setString("name", name);
		int result = ((Long)query.list().get(0)).intValue();
		if(result>0){
			return true;
		}
		return false;
	}
	

}
