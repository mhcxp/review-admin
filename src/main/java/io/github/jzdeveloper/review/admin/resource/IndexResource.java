package io.github.jzdeveloper.review.admin.resource;

import io.github.jzdeveloper.review.admin.base.MediaTypeUTF8;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("")
@Produces(MediaTypeUTF8.TEXT_PLAIN_UTF8)
public class IndexResource {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexResource.class);
	
	/**
	 * 重定向到index.html界面上
	 * @param request
	 * @param response
	 */
	@Path("/")
	@GET
	public void index(@Context HttpServletRequest request,@Context HttpServletResponse response){
		//return new Viewable("/public/app/index.html",null);
		try {
			response.sendRedirect("/public/app/index.html");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	

}
