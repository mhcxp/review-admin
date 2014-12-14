package io.github.jzdeveloper.review.admin.service;

import io.github.jzdeveloper.review.admin.base.BusinessException;
import io.github.jzdeveloper.review.admin.dao.IssueDAO;
import io.github.jzdeveloper.review.admin.dao.ReviewDAO;
import io.github.jzdeveloper.review.admin.model.Issue;
import io.github.jzdeveloper.review.admin.model.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReviewService {
	
	public ReviewService(ReviewDAO dao, IssueDAO issueDao){
		this.dao = dao;
		this.issueDao = issueDao;
	}
	
	private final ReviewDAO dao ;
	private final IssueDAO issueDao;
	
	/**
	 * 1. 接收客户端传递的reveiw数据，与数据库比较，找到要更新到数据库的review或issue，统一更新到数据
	 * 2. 从数据库查询所有的活动数据，返回
	  */
	public List<Review> reviewSync(List<Review> clientReviews)throws BusinessException{
		//1. 同步更新到服务器
		if(clientReviews!=null&&clientReviews.size()>0){
			List<Review> delReviews = dao.findClosedReview(clientReviews);
			if(delReviews!=null&&delReviews.size()>0){
				clientReviews = this.excludeDelReview(clientReviews, delReviews);
			}
			//循环
			merge(clientReviews);
		}
		//2. 返回当前最新数据,重新从数据库查询！
		return dao.findActiveReview();
	}
	
	/**
	 * 去掉已经删除掉的
	 * @param clientReviews
	 * @param delReviews
	 * @return
	 */
	private List<Review> excludeDelReview(List<Review> clientReviews , List<Review> delReviews){
		List<String> ids = new ArrayList<String>();
		for(Review review : delReviews){
			ids.add(review.getId());
		}
		List<Review> result  = new ArrayList<Review>();
		for(Review review : clientReviews){
			if(!ids.contains(review.getId())){
				result.add(review);
			}
		}
		return result;
	}
	
	/**
	 * 将客户端的数据合并到服务器上，查询比较
	 * @param clientReviews
	 * @return
	 */
	private void merge(List<Review> clientReviews)throws BusinessException{
		List<Review> serverReviews = dao.findActiveReview();
		Map<String,Review> servermaps = this.getMap(serverReviews);
		List<Review> insertReviews = new ArrayList<Review>();//要插入到服务器上的数据
		List<Issue> issues = new ArrayList<Issue>();
		for(Review review : clientReviews){
			//客户端的数据在服务器上没有，则表示是需要更新到服务器上的
			if(!servermaps.keySet().contains(review.getId())){
				insertReviews.add(review);
			}else{//找到是否有需要更新到服务器上的数据,比较两者的
				List<Issue> iou = this.compareToServer(review, servermaps.get(review.getId()));
				issues.addAll(iou);
			}
		}//end for loop
		
		//持久化review
		if(insertReviews!=null&&insertReviews.size()>0){
			dao.saveOrUpdate(insertReviews);
		}
		//持久化issue
		if(issues!=null&&issues.size()>0){
			issueDao.saveOrUpdate(issues);
		}
	}
	
	/**
	 * 以Id为主键，生成相应的Map结构
	 * @param reviews
	 * @return
	 */
	private Map<String,Review> getMap(List<Review> reviews){
		Map<String,Review> result = new HashMap<String,Review>();
		for(Review review : reviews){
			result.put(review.getId(), review);
		}
		return result;
	}
	
	/**
	 * 将客户端review与服务器端的review做比较，如果客户端的比较新，则返回更插入或更新的issue
	 * <strong>目前只比较issue</strong>
	 * @param client
	 * @param server
	 * @return 要更新或插入到服务器上的数据
	 */
	private List<Issue> compareToServer(Review client,Review server){
		List<Issue> result = new ArrayList<Issue>();
		Map<String,Issue> clientIssues = getIssueMap(client.getIssues());
		Map<String,Issue> serverIssues = getIssueMap(server.getIssues());
		for(Entry<String,Issue> entry : clientIssues.entrySet()){
			//1 找到服务器上不存在的，从而是要更新的
			if(!serverIssues.containsKey(entry.getKey())){
				Issue issue = entry.getValue();
				issue.setReview(server);
				result.add(issue);
			}else{
			//2 是否有需要更新到服务器的issue
				Issue s = serverIssues.get(entry.getKey());
				//客户端上的Issue的最后更新时间要大于服务器上的时间，并且服务器上的Issue没有关闭
				if(s!=null&&
					entry.getValue().getLastModificationDate().after(s.getLastModificationDate())&&
					!s.isClosed()){
					Issue issue = entry.getValue();
					s.setAnnotation(issue.getAnnotation()).setAssignedTo(issue.getAssignedTo())
						.setDescription(issue.getDescription()).setFileLine(issue.getFileLine())
						.setFileName(issue.getFileName()).setLastModificationDate(issue.getLastModificationDate())
						.setResolution(issue.getResolution()).setReviewerId(issue.getReviewerId())
						.setRevision(issue.getRevision()).setSeverity(issue.getSeverity())
						.setStatus(issue.getStatus()).setSummary(issue.getSummary())
						.setType(issue.getType());
					result.add(s);
				}
			}//end of else
		}//end for loop 
		return result;
	}
	
	private Map<String,Issue> getIssueMap(List<Issue> issues){
		Map<String,Issue> result = new HashMap<String,Issue>();
		for(Issue issue : issues){
			result.put(issue.getId(), issue);
		}
		return result;
	}
	public ReviewDAO getDao() {
		return dao;
	}
	
	public IssueDAO getIssueDao() {
		return issueDao;
	}

}
