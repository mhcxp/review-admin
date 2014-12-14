package io.github.jzdeveloper.review.admin.config;

import io.dropwizard.db.DataSourceFactory;
import io.github.jzdeveloper.review.admin.model.FieldItem;
import io.github.jzdeveloper.review.admin.model.File;
import io.github.jzdeveloper.review.admin.model.Filter;
import io.github.jzdeveloper.review.admin.model.Issue;
import io.github.jzdeveloper.review.admin.model.Phase;
import io.github.jzdeveloper.review.admin.model.Project;
import io.github.jzdeveloper.review.admin.model.ProjectStatus;
import io.github.jzdeveloper.review.admin.model.Review;
import io.github.jzdeveloper.review.admin.model.Reviewer;
import io.github.jzdeveloper.review.admin.model.Role;
import io.github.jzdeveloper.review.admin.model.Task;
import io.github.jzdeveloper.review.admin.model.TaskStatus;
import io.github.jzdeveloper.review.admin.model.TaskType;
import io.github.jzdeveloper.review.admin.model.User;

/**
 * hibernate绑定的相关处理功能
 * 
 * @author mazhyb
 *
 */
public class HibernateBundle extends
		io.dropwizard.hibernate.HibernateBundle<ReviewAdminConfiguration> {
	
	private static final HibernateBundle hibernateBundle = new HibernateBundle(User.class,
			new Class[] { Review.class, Reviewer.class,Phase.class, Issue.class, Filter.class,File.class, FieldItem.class,
							Project.class,ProjectStatus.class,TaskType.class,TaskStatus.class,Task.class,
							Role.class
	});
	public static HibernateBundle getInstance() {
		return hibernateBundle;
	}

	protected HibernateBundle(Class<?> entity, Class<?>[] entities) {
		super(entity, entities);
	}

	@Override
	public DataSourceFactory getDataSourceFactory(
			ReviewAdminConfiguration configuration) {
		return configuration.getDataSourceFactory();
	}

}
