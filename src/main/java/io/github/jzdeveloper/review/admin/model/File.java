package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "file")
public class File extends AbstractModel<File> {

	private static final long serialVersionUID = -9086663557627191731L;


	private String name;

	public String getName() {

		return name;
	}

	public File setName(String name) {
		this.name = name;
		return this;
	}

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Review review;

	public Review getReview() {
		return review;
	}

	public File setReview(Review review) {
		this.review = review;
		return this;
	}

}
