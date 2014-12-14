package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reviewer")
public class Reviewer  extends AbstractModel<Reviewer>{

	private static final long serialVersionUID = -3884324608275944814L;

	private String id;
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinTable(name = "review_reviewer", joinColumns = { @JoinColumn(name ="reviewer_pid" )}, inverseJoinColumns = { @JoinColumn(name = "review_pid") })
    @OrderBy("pid")
	private List<Review> reviews;


	public String getId() {
		return id;
	}

	public Reviewer setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Reviewer setName(String name) {
		this.name = name;
		return this;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public Reviewer setReviews(List<Review> reviews) {
		this.reviews = reviews;
		return this;
	}
	
	
	
}
