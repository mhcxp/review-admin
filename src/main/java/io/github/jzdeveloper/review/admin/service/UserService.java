package io.github.jzdeveloper.review.admin.service;

import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.dao.UserDAO;
import io.github.jzdeveloper.review.admin.model.User;
import io.github.jzdeveloper.review.admin.util.CryptUtil;

import org.apache.commons.lang.StringUtils;

public class UserService {

	public UserService(UserDAO dao){
		this.dao = dao;
	}
	private final UserDAO dao;
	
	public User add(User user) throws BusinessException {
		// 1 用户名不为空
		if (StringUtils.isEmpty(user.getName())) {
			throw new BusinessException("保存失败！用户名不能为空！");
		}
		// 2 密码不能为空
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new BusinessException("保存失败！密码不能为空！");
		}
		// 3 用户名不能重复
		boolean has = this.dao.hasSameName(user.getName());
		if(has){
			throw new BusinessException("保存失败！已经存在该用户名"+user.getName()+"！");
		}
		try {
			//加密密码
			user.setPassword(CryptUtil.encrypt(user.getPassword()));
			return this.dao.saveOrUpdate(user);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
}
