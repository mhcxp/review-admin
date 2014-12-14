package io.github.jzdeveloper.review.admin.resource;

import io.dropwizard.hibernate.UnitOfWork;
import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;
import io.github.jzdeveloper.review.admin.base.Message;
import io.github.jzdeveloper.review.admin.model.Review;
import io.github.jzdeveloper.review.admin.service.ReviewService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;

@Path("/api")
@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
public class APIResource {
	
	private Logger logger  = LoggerFactory.getLogger(APIResource.class);

	public APIResource(ReviewService service){
		this.service = service;
	}
	
	private final ReviewService service;
	
	/**
	 * 接收客户端发送的完整的reveiw数据，合并到数据库服务器上，然后从服务器下载完整的review后返回前台
	 * @return
	 */
	@Path("/sync")
	@POST
	@Produces(MediaTypeUTF8.APPLICATION_JSON_UTF8)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed
	@UnitOfWork
	public Message reviewSync(List<Review> clientReviews,@Context HttpServletRequest request){
			logger.debug("接收请求，进行数据初始化！");
		try {
			List<Review> reviews = service.reviewSync(clientReviews);
			Message msg = new Message().setSuccess(true).setData(reviews);
			System.out.println(JSON.toJSONString(msg));
			return msg;
		} catch (BusinessException e) {
			return new Message().setSuccess(false).setMsg("同步失败，原因是："+e.getMessage());
		}
			
	}
	

	
}
