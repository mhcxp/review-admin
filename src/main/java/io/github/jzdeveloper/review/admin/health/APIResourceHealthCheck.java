package io.github.jzdeveloper.review.admin.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * api接口健康检查
 * @author mazhaoyong
 *
 */
public class APIResourceHealthCheck extends HealthCheck{

	

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
