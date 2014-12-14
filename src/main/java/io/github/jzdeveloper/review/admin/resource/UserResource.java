package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.base.BaseResource;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.dao.UserDAO;
import io.github.jzdeveloper.review.admin.model.User;
import io.github.jzdeveloper.review.admin.service.UserService;
import io.github.jzdeveloper.review.admin.util.CryptUtil;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;


@Path("/user")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class UserResource extends BaseResource<User>{
	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);
	private final UserDAO dao;
	private final UserService service;
	public UserResource(UserDAO dao){
		this.dao = dao;
		this.service = new UserService(dao);
	}

	@Path("/page/{index}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message page(@PathParam("index") int index){
		return super.page(index);
	}
	
	@Path("/{pid}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message get(@PathParam("pid") long pid){
		try {
			User u = this.dao.getById(pid);
			return this.ok(u);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.fail("查询失败！"+e.getMessage());
		}
	}
	
	@Path("/add")
	@POST
	@Timed
	@Consumes(MediaTypeUTF8.APPLICATION_JSON_UTF8)
	@UnitOfWork(transactional=true)
	public Message add(User user){
		try {
			User u = this.service.add(user);
			return this.ok(u);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return this.fail(e.getMessage());
		}
	}
	
	@Path("/edit")
	@POST
	@Timed
	@Consumes(MediaTypeUTF8.APPLICATION_JSON_UTF8)
	@UnitOfWork(transactional=true)
	public Message edit(User user){
		try {
			User source = this.dao.getById(user.getPid());
			source.setName(user.getName()).setNickname(user.getNickname()).setEmail(user.getEmail());
			User u = this.dao.saveOrUpdate(source);
			return this.ok(u);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return this.fail(e.getMessage());
		}
	}
	
	@Path("/del/{pid}")
	@GET
	@Timed
	@UnitOfWork(transactional=true)
	public Message delete(@PathParam("pid") long pid){
		if(pid==0){
			return this.fail("删除失败！请求用户不存在！");
		}
		try {
			this.dao.deleteById(pid);
			return new Message(true);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.fail(e.getMessage());
		}
	}
	
	@Path("/changepwd")
	@POST
	@Timed
	@Consumes(MediaTypeUTF8.APPLICATION_JSON_UTF8)
	@UnitOfWork(transactional=true)
	public Message changePwd(Map<String,String> params/**包含要修改的用户有pid 新密码  重复密码*/){
		String pidStr = params.get("pid");
		Long pid = new Long(pidStr);
		String newpwd = params.get("newpwd");
		String newpwd2 = params.get("newpwd2");
		if(newpwd==null||newpwd2==null){
			return this.fail("密码不能为空！");
		}
		if(!newpwd.equals(newpwd2)){
			return this.fail("保存失败！两次密码不相等！");
		}
		User source = this.dao.getById(pid);
		if(source==null){
			return this.fail("未找到该用户！");
		}
		try {
			source.setPassword(CryptUtil.encrypt(newpwd));
			User u = this.dao.saveOrUpdate(source);
			return this.ok(u);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.fail(e.getMessage());
		} 
		
	}
	
	
	
	@Override
	public BaseDAO<User> getBaseDAO() {
		return this.dao;
	}
	
	

}
