package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.base.Page;
import io.github.jzdeveloper.review.admin.model.Review;
import io.github.jzdeveloper.review.admin.service.ReviewService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.codahale.metrics.annotation.Timed;

/**
 * review相关资源
 * @author mazhyb
 *
 */
@Path("/review")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class ReviewResource {

	public ReviewResource(ReviewService service){
		this.service = service;
	}
	
	private ReviewService service;
	
	
	/**
	 * 分页功能，接收分页请求，并返回当前分页的内容
	 * @param index
	 * @return
	 */
	@Path("/page/{index}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message  page(@PathParam("index") int index){
		try {
			Page<Review> page = this.service.getDao().page(index);
			return new Message().setSuccess(true).setData(page);
		} catch (BusinessException e) {
			return new Message().setSuccess(false).setMsg("查询失败！原因是："+e!=null?e.getMessage():"");
		}
	}
	
	@Path("/close/{pid}")
	@POST
	@Timed
	@UnitOfWork(transactional=true)
	public Message close(@PathParam("pid") long pid){
		try {
			Review review = this.service.getDao().getById(pid);
			review.setClose(true);
			this.service.getDao().saveOrUpdate(review);
			return new Message().setSuccess(true).setMsg("关闭成功");
		} catch (BusinessException e) {
			return new Message().setSuccess(false).setMsg("查询失败！原因是："+e!=null?e.getMessage():"");
		}
	}
	
	@Path("/active/{pid}")
	@POST
	@Timed
	@UnitOfWork(transactional=true)
	public Message active(@PathParam("pid") long pid){
		try {
			Review review = this.service.getDao().getById(pid);
			review.setClose(false);
			this.service.getDao().saveOrUpdate(review);
			return new Message().setSuccess(true).setMsg("开启成功");
		} catch (BusinessException e) {
			return new Message().setSuccess(false).setMsg("查询失败！原因是："+e!=null?e.getMessage():"");
		}
	}
	
	
	@Path("/{pid}")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Review get(@PathParam("pid") long pid){
		return this.service.getDao().getById(pid);
	}
	
	
	//获取chart信息
	@Path("/chart")
	@GET
	@Timed
	@UnitOfWork(readOnly=true)
	public Message chart(){
		return new Message().setSuccess(true).setData(this.service.getDao().chart());
	}
	
	
	
}
