package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.base.BaseResource;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.dao.RoleDAO;
import io.github.jzdeveloper.review.admin.model.Role;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;


@Path("/role")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class RoleResource extends BaseResource<Role>{
	private static final Logger logger = LoggerFactory.getLogger(RoleResource.class);
	public RoleResource(RoleDAO dao){
		this.dao = dao;
	}
	private RoleDAO dao;
	@Override
	public BaseDAO<Role> getBaseDAO() {
		return this.dao;
	}
	
	@Path("/page/{index}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message page(@PathParam("index") int index){
		return super.page(index);
	}
	
	@Path("/add")
	@POST
	@Timed
	@Consumes(MediaTypeUTF8.APPLICATION_JSON_UTF8)
	@UnitOfWork(transactional=true)
	public Message add(Role role){
		try {
			Role r = this.dao.saveOrUpdate(role);
			return this.ok(r);
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
	public Message edit(Role role){
		try {
			Role source = this.dao.getById(role.getPid());
			source.setName(role.getName()).setDescription(role.getDescription());
			Role r = this.dao.saveOrUpdate(source);
			return this.ok(r);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return this.fail(e.getMessage());
		}
	}
	
	@Path("/del/{pid}")
	@GET
	@Timed
	@UnitOfWork(transactional = true)
	public Message delete(@PathParam("pid") long pid) {
		if (pid == 0) {
			return this.fail("删除失败！请求用户不存在！");
		}
		try {
			this.dao.deleteById(pid);
			return new Message(true);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return this.fail(e.getMessage());
		}
	}
	
	@Path("/{pid}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message get(@PathParam("pid") long pid){
		try {
			Role r = this.dao.getById(pid);
			return this.ok(r);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.fail("查询失败！"+e.getMessage());
		}
	}
	
	
	
}
