package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.base.BaseResource;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.dao.TaskDAO;
import io.github.jzdeveloper.review.admin.model.Task;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/task")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class TaskResource extends BaseResource<Task> {

	private static final Logger logger = LoggerFactory.getLogger(TaskResource.class);
	private final TaskDAO dao;
	public TaskResource(TaskDAO dao){
		this.dao = dao;
	}
	
	@Override
	public BaseDAO<Task> getBaseDAO() {
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
	public Message add(Task task){
		try {
			task.setCreated(new Date());
			Task r = this.dao.saveOrUpdate(task);
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
	public Message edit(Task task){
		try {
			Task source = this.dao.getById(task.getPid());
			source.setAssignTo(task.getAssignTo()).setDescription(task.getDescription())
				.setEnd(task.getEnd()).setStart(task.getStart()).setStatus(task.getStatus())
				.setTitle(task.getTitle()).setTypes(task.getTypes());
			Task r = this.dao.saveOrUpdate(source);
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
	
	@Path("/{pid}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message get(@PathParam("pid") long pid){
		try {
			Task r = this.dao.getById(pid);
			return this.ok(r);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.fail("查询失败！"+e.getMessage());
		}
	}
	
	
	
	
}
