package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author mazhyb
 */
@Entity
@Table(name = "fielditem")
public class FieldItem  extends AbstractModel<FieldItem> {

	private static final long serialVersionUID = -5476381888255845404L;

	private String id;
	
	private String defaultItem;
	
	@Column(length=500)
	private String value;
	
	/**
	 * 字段是针对value进行拆分，按“;"分隔，用于显示用，不保存到数据库
	 */
	@Transient
	private List<String> items;
	
	

	public String getId() {
		return id;
	}

	public FieldItem setId(String id) {
		this.id = id;
		return this;
	}

	public String getDefaultItem() {
		return defaultItem;
	}

	public FieldItem setDefaultItem(String defaultItem) {
		this.defaultItem = defaultItem;
		return this;
	}

	
	public List<String> getItems() {
		if(this.items==null){
			if(!StringUtils.isEmpty(value)){
				this.items = new ArrayList<String>();
				String[] is = value.split(";");
				for(String s : is){
					this.items.add(s);
				}
			}
		}
		
		return items;
	}

	public FieldItem setItems(List<String> items) {
		this.items = items;
		return this;
	}
	
	public String getValue() {
		return value;
	}
	
	public FieldItem setValue(String value) {
		this.value = value;
		return this;
	}
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Review review;

	public Review getReview() {
		return review;
	}

	public FieldItem setReview(Review review) {
		this.review = review;
		return this;
	}
	
	
}
