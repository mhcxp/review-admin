package io.github.jzdeveloper.review.admin.base;

import java.util.List;

/**
 * 分页对象
 * @author mazhyb
 *
 * @param <E>
 */
public class Page<E> implements java.io.Serializable {

	private static final long serialVersionUID = 403268541505617439L;

	/**
	 * 默认的分页大小，每页显示的数据记录
	 */
	public static final int PAGE_SIZE = 50;
	
	/**
	 * 
	 * @param pageNo 请求页码
	 * @param rowsCount 总页数
	 */
	public Page(int pageNo,int rowsCount){
		this(pageNo,PAGE_SIZE,rowsCount);
	}
	
	public Page(int pageNo,int pageSize,int rowsCount){
		this.index = pageNo;
		this.rowsCount = rowsCount;
		this.pageSize = pageSize;
		init();
	}
	
	/**
	 * 初始化相关数据
	 */
	private void init(){
		if(this.rowsCount<=this.pageSize){
			pageCount = 1;
			index = 1;
			this.first = true;
			this.last = true;
		}else{
			int left = this.rowsCount % this.pageSize;
			this.pageCount = this.rowsCount / this.pageSize;
			if(left>0){
				this.pageCount++;
			}
		}
		this.start = (this.index - 1)*this.pageSize;
		this.end = this.index * this.pageSize;
	}
	
	/**
	 * 当前请求的页码
	 */
	private int index;
	
	private int rowsCount;//总记录数
	
	private int pageCount;//总页数
	
	private int pageSize;//每页显示的记录数
	
	private boolean first;//是否第1页
	
	private boolean last;//是否最后一页
	
	private int start;//起始页码
	
	private int end;//结束页码
	
	private List<E> content;//结果保存

	public int getIndex() {
		return index;
	}

	public Page<E> setIndex(int index) {
		this.index = index;
		return this;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public Page<E> setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
		return this;
	}

	public int getPageCount() {
		return pageCount;
	}

	public Page<E> setPageCount(int pageCount) {
		this.pageCount = pageCount;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Page<E> setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public boolean isFirst() {
		return first;
	}

	public Page<E> setFirst(boolean first) {
		this.first = first;
		return this;
	}

	public boolean isLast() {
		return last;
	}

	public Page<E> setLast(boolean last) {
		this.last = last;
		return this;
	}

	public int getStart() {
		return start;
	}

	public Page<E> setStart(int start) {
		this.start = start;
		return this;
	}

	public int getEnd() {
		return end;
	}

	public Page<E> setEnd(int end) {
		this.end = end;
		return this;
	}

	public List<E> getContent() {
		return content;
	}

	public Page<E> setContent(List<E> content) {
		this.content = content;
		return this;
	}
	
	
	
	
	
	
}
