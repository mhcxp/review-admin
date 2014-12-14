package io.github.jzdeveloper.review.admin.dao;

import io.github.jzdeveloper.review.admin.base.BaseDAO;
import io.github.jzdeveloper.review.admin.model.Review;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.google.common.collect.Lists;

public class ReviewDAO extends BaseDAO<Review> {

	public ReviewDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * 指定review，查询是否存在已经关闭的review
	 * 
	 * @return
	 */
	public List<Review> findClosedReview(List<Review> clientReviews) {
		Query query = this.currentSession().createQuery(
				"select new Review(pid,id) from Review where isClose = true and id in ("
						+ inSql(clientReviews) + ")");
		return this.list(query);
	}

	private String inSql(List<Review> clientReviews) {
		if (clientReviews == null || clientReviews.size() == 0) {
			return "''";
		}
		StringBuffer sb = new StringBuffer();
		for (Review review : clientReviews) {
			sb.append("'").append(review.getId()).append("',");
		}
		return sb.substring(0, sb.length() - 1);
	}




	/*
	public Review saveOrUpdate(Review review) {
		Review result = this.persist(review);
		this.currentSession().flush();
		this.currentSession().clear();
		return result;
	}*/

	/**
	 * 查询所有的关闭
	 * 
	 * @return
	 */
	public List<Review> findClosedReview() {
		return this.findReview(true);
	}

	public List<Review> findActiveReview() {
		return this.findReview(false);
	}

	public List<Review> findReview(boolean isClose) {
		Query query = this.currentSession().createQuery(
				"from Review where isClose=:isClose");
		query.setBoolean("isClose", isClose);
		return this.list(query);
	}
	
	public List<Long> chart(){
		 Long r1 = ((Long)currentSession().createQuery("select count(*) from Review where isClose = false").list().get(0));
		 Long r2 = ((Long)currentSession().createQuery("select count(*) from Review where isClose = true").list().get(0));
		 List<Long> result = Lists.newArrayList(r1,r2);	 
		 return result;
	}
	
	
	

}
