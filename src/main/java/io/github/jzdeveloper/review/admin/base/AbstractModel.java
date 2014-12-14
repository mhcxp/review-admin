package io.github.jzdeveloper.review.admin.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractModel<E> implements Serializable{

	private static final long serialVersionUID = 1514681759990197587L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long pid;
	
	public Long getPid() {
		return pid;
	}
	
	
	@SuppressWarnings("unchecked")
	public E setPid(Long pid) {
		this.pid = pid;
		return (E) this;
	}
	
}
