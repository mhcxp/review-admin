package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.base.BaseResource;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.dao.TaskStatusDAO;
import io.github.jzdeveloper.review.admin.model.TaskStatus;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;


@Path("/taskstatus")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class TaskStatusResource extends BaseResource<TaskStatus>{

	private static final Logger logger = LoggerFactory.getLogger(TaskStatusResource.class);
	
	public TaskStatusResource(TaskStatusDAO dao){
		this.dao = dao;
	}
	
	private final TaskStatusDAO dao;
	
	@Override
	public BaseDAO<TaskStatus> getBaseDAO() {
		return this.dao;
	}
	
	@Path("/{pid}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message get(@PathParam("pid") long pid){
		try {
			TaskStatus r = this.dao.getById(pid);
			return this.ok(r);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.fail("查询失败！"+e.getMessage());
		}
	}
	
	@Path("/all")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message all(){
		try {
			List<TaskStatus> result = this.dao.findAll();
			return this.ok(result);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return this.fail("查询失败！"+e.getMessage());
		}
	}
	
	@Path("/add")
	@POST
	@Timed
	@Consumes(MediaTypeUTF8.APPLICATION_JSON_UTF8)
	@UnitOfWork(transactional=true)
	public Message add(TaskStatus status){
		try {
			TaskStatus r = this.dao.saveOrUpdate(status);
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
	public Message edit(TaskStatus status){
		try {
			TaskStatus source = this.dao.getById(status.getPid());
			source.setDescription(status.getDescription()).setName(status.getName());
			TaskStatus r = this.dao.saveOrUpdate(source);
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
			return this.fail("删除失败！请求数据不存在！");
		}
		try {
			this.dao.deleteById(pid);
			return new Message(true);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return this.fail(e.getMessage());
		}
	}
	

}
