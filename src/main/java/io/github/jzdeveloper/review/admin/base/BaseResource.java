package io.github.jzdeveloper.review.admin.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 提供通用的接口方法，在具体的resource中，只是需要去掉用
 * @author mazhyb
 *
 * @param <E>
 */
public abstract class BaseResource<E> {

	private static final Logger logger = LoggerFactory.getLogger(BaseResource.class);

	
	public E getById(long id){
		return this.getBaseDAO().getById(id);
	}
	
	public Message page(int index){
		try {
			Page<E> page = this.getBaseDAO().page(index);
			return new Message(true).setData(page);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return new Message(false);
		}
	}
	
	
	
	public abstract BaseDAO<E> getBaseDAO();
	
	
	public Message ok(Object obj){
		return new Message(true).setData(obj);
	}
	
	public Message fail(String msg){
		return new Message(false).setMsg(msg);
	}
	
	
}
