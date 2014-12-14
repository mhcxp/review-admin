package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.base.Page;
import io.github.jzdeveloper.review.admin.dao.IssueDAO;
import io.github.jzdeveloper.review.admin.model.Issue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

/**
 * issue查询服务
 * 
 * @author mazhyb
 */


@Path("/issue")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class IssueResource {

	public IssueResource(IssueDAO dao){
		this.dao = dao;
	}
	
	private final IssueDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(IssueResource.class);
	
	@Path("/page/{index}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message page(@PathParam("index") int index){
		try {
			Page<Issue> page = dao.page(index);
			return new Message().setSuccess(true).setData(page);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return new Message().setSuccess(false).setMsg(e.getMessage());
		}
	}
	
	/**
	 * 关闭issue
	 * @param pid
	 * @return
	 */

	@Path("/close/{pid}")
	@Timed
	@UnitOfWork(transactional=true)
	public Message close(@PathParam("pid") long pid){
		try {
			Issue issue = this.dao.getById(pid);
			issue.close();
			this.dao.saveOrUpdate(issue);
			return new Message().setSuccess(true);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return new Message().setSuccess(false).setMsg(e.getMessage());
		}
	}
	
	
	
}
