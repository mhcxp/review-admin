package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.base.BaseResource;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.dao.ProjectDAO;
import io.github.jzdeveloper.review.admin.model.Project;

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

@Path("/project")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class ProjectResource extends BaseResource<Project>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectResource.class);
	public ProjectResource(ProjectDAO dao){
		this.dao = dao;
	}
	private ProjectDAO dao;
	
	@Override
	public BaseDAO<Project> getBaseDAO() {
		return this.dao;
	}
	
	@Path("/{pid}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message get(@PathParam("pid") long pid){
		try {
			Project r = this.dao.getById(pid);
			return this.ok(r);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return this.fail("查询失败！"+e.getMessage());
		}
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
	public Message add(Project project){
		try {
			project.setCreationTime(new Date());
			Project r = this.dao.saveOrUpdate(project);
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
	public Message edit(Project project){
		try {
			Project source = this.dao.getById(project.getPid());
			source.setDescription(project.getDescription())
				.setEnd(project.getEnd()).setLeader(project.getLeader())
				.setName(project.getName()).setStart(project.getStart());
			Project r = this.dao.saveOrUpdate(source);
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
