package io.github.jzdeveloper.review.admin;

import io.dropwizard.setup.Environment;
import io.github.jzdeveloper.review.admin.config.HibernateBundle;
import io.github.jzdeveloper.review.admin.config.ReviewAdminConfiguration;
import io.github.jzdeveloper.review.admin.dao.IssueDAO;
import io.github.jzdeveloper.review.admin.dao.ProjectDAO;
import io.github.jzdeveloper.review.admin.dao.ProjectStatusDAO;
import io.github.jzdeveloper.review.admin.dao.ReviewDAO;
import io.github.jzdeveloper.review.admin.dao.RoleDAO;
import io.github.jzdeveloper.review.admin.dao.TaskDAO;
import io.github.jzdeveloper.review.admin.dao.TaskStatusDAO;
import io.github.jzdeveloper.review.admin.dao.TaskTypeDAO;
import io.github.jzdeveloper.review.admin.dao.UserDAO;
import io.github.jzdeveloper.review.admin.health.APIResourceHealthCheck;
import io.github.jzdeveloper.review.admin.resource.APIResource;
import io.github.jzdeveloper.review.admin.resource.IndexResource;
import io.github.jzdeveloper.review.admin.resource.IssueResource;
import io.github.jzdeveloper.review.admin.resource.ProjectResource;
import io.github.jzdeveloper.review.admin.resource.ProjectStatusResource;
import io.github.jzdeveloper.review.admin.resource.ReviewResource;
import io.github.jzdeveloper.review.admin.resource.RoleResource;
import io.github.jzdeveloper.review.admin.resource.TaskResource;
import io.github.jzdeveloper.review.admin.resource.TaskStatusResource;
import io.github.jzdeveloper.review.admin.resource.TaskTypeResource;
import io.github.jzdeveloper.review.admin.resource.UserResource;
import io.github.jzdeveloper.review.admin.service.ReviewService;

import org.hibernate.SessionFactory;

/**
 * application初始化操作相关功能
 * 
 * @author mazhyb
 *
 */
public final class ApplicationInit {

	private ApplicationInit(){
		
	}
	
	/**
	 * 将ReviewAdminApplication中的run方法的部分内容转移过来
	 * @param configuration
	 * @param environment
	 * @throws Exception
	 */
	public static void init(ReviewAdminConfiguration configuration,
			Environment environment) throws Exception{
		//1 初始化hibernate
		final HibernateBundle hibernateBundle =  HibernateBundle.getInstance();
		hibernateBundle.run(configuration, environment);
		
		final SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
		//dao
		final ReviewDAO reviewDao = new ReviewDAO(sessionFactory);
		final IssueDAO issueDao = new IssueDAO(sessionFactory);
		
		final ReviewService reviewService = new ReviewService(reviewDao,issueDao);
		//api resource
		final APIResource api = new APIResource(reviewService);
		final IndexResource index = new IndexResource();
		//review resource
		final ReviewResource review = new ReviewResource(reviewService);
				
		environment.jersey().register(api);
		environment.jersey().register(index);
		environment.jersey().register(review);
		
		//issue
		final IssueResource issueRes = new IssueResource(issueDao);
		environment.jersey().register(issueRes);
		
		//user
		final UserDAO userDAO = new UserDAO(sessionFactory);
		final UserResource userRes = new UserResource(userDAO);
		environment.jersey().register(userRes);
		
		//role
		final RoleDAO roleDAO = new RoleDAO(sessionFactory);
		final RoleResource roleRes = new RoleResource(roleDAO);
		environment.jersey().register(roleRes);
		
		//project
		final ProjectDAO projDAO = new ProjectDAO(sessionFactory);
		final ProjectResource projRes = new ProjectResource(projDAO);
		environment.jersey().register(projRes);
		final ProjectStatusDAO projStatusDAO = new ProjectStatusDAO(sessionFactory);
		final ProjectStatusResource projStatusRes = new ProjectStatusResource(projStatusDAO);
		environment.jersey().register(projStatusRes);
		
		//task
		final TaskStatusDAO taskStatusDAO = new TaskStatusDAO(sessionFactory);
		final TaskStatusResource taskStatusRes = new TaskStatusResource(taskStatusDAO);
		environment.jersey().register(taskStatusRes);
		final TaskTypeDAO taskTypeDAO = new TaskTypeDAO(sessionFactory);
		final TaskTypeResource taskTypeRes = new TaskTypeResource(taskTypeDAO);
		environment.jersey().register(taskTypeRes);
		final TaskDAO taskDAO = new TaskDAO(sessionFactory);
		final TaskResource taskRes = new TaskResource(taskDAO);
		environment.jersey().register(taskRes);
		
		
		
		
		initHealthCheck(configuration,environment);
	}
	
	private static void initHealthCheck(ReviewAdminConfiguration configuration,Environment environment) throws Exception{
		// health check
		final APIResourceHealthCheck apicheck = new APIResourceHealthCheck();
		environment.healthChecks().register("api", apicheck);
	}
	
	
}
