package io.github.jzdeveloper.review.admin;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.github.jzdeveloper.review.admin.config.HibernateBundle;
import io.github.jzdeveloper.review.admin.config.PublicResourceBundle;
import io.github.jzdeveloper.review.admin.config.ReviewAdminConfiguration;

public class ReviewAdminApplication   extends Application<ReviewAdminConfiguration>{

	private final PublicResourceBundle pubBundle = new PublicResourceBundle();

	@Override
	public void initialize(Bootstrap<ReviewAdminConfiguration> bootstrap) {
		//添加公共资源访问
		bootstrap.addBundle(pubBundle);
		bootstrap.addBundle(HibernateBundle.getInstance());
	}

	@Override
	public void run(ReviewAdminConfiguration configuration,
			Environment environment) throws Exception {
		ApplicationInit.init(configuration, environment);
	}
	
	@Override
	public String getName() {
		return "review-admin";
	}
	
	
	public static void main(String[] args) throws Exception {
		new ReviewAdminApplication().run(new String[]{"server","src/main/resources/review-admin.yml"});
	}

}
