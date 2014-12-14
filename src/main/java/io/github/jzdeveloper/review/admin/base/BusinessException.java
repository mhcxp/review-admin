package io.github.jzdeveloper.review.admin.base;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = -7902377449477664417L;

	
	public BusinessException(String msg){
		super(msg);
	}
	
	public BusinessException(Throwable cause){
		super(cause);
	}
	
	public BusinessException(String msg,Throwable cause){
		super(msg,cause);
	}
	
	
}
