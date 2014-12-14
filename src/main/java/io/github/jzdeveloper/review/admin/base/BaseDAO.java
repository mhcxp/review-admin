package io.github.jzdeveloper.review.admin.base;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDAO<E> extends AbstractDAO<E> {

	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	public BaseDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	public int getTotalCount()throws BusinessException{
		try {
			return ((Long)currentSession().createQuery("select count(*) from "+ this.getEntityClass().getSimpleName()).list().get(0)).intValue();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findAll()throws BusinessException{
		try {
			return this.currentSession().createQuery(" from "+ this.getEntityClass().getSimpleName()).list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		}
	}
	
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @return
	 */
	public Page<E> page(int pageNo)throws BusinessException{
		Page<E> page;
		try {
			page = new Page<E>(pageNo,this.getTotalCount());
			page.setContent(this.findAll());
			return page;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		}
	}
	
	public E saveOrUpdate(E entity)throws BusinessException{
		try {
			E result = this.persist(entity);
			return result;
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			throw new BusinessException(e);
		}
	}
	
	public List<E> saveOrUpdate(List<E> entities)throws BusinessException{
		List<E> result = new ArrayList<E>();
		for(E entity : entities){
			result.add(this.saveOrUpdate(entity));
		}
		return result;
	}
	
	public E getById(long id){
		return this.get(id);
	}
	
	public void deleteById(long id){
		this.currentSession().delete(this.getById(id));
	}
	
	
	

}
