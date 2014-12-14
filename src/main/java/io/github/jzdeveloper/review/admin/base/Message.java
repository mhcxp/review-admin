package io.github.jzdeveloper.review.admin.base;

/**
 * 提供给前台的数据封装对象
 * 
 * @author mazhyb
 *
 */
public class Message implements java.io.Serializable{

	private static final long serialVersionUID = 2679031399783726793L;

	public Message(){}
	
	public Message(boolean success){
		this.success = success;
	}
	
	/**
	 * 操作成功或失败
	 */
	private boolean success;
	
	/**
	 * 特殊标识，用于区分不同的处理失败原因
	 */
	private int code;
	
	/**
	 * 提示信息
	 */
	private String msg;
	
	/**
	 * 返回数据
	 */
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public Message setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public int getCode() {
		return code;
	}

	public Message setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Message setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Message setData(Object data) {
		this.data = data;
		return this;
	}
	
	
	

}
