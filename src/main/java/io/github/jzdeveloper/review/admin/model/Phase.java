package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author mazhyb
 *
 */
@Entity
@Table(name = "phase")
public class Phase extends AbstractModel<Phase> {

	private static final long serialVersionUID = 3001683601602439851L;

	private String name;
	private boolean enable = true;

	@OneToMany(fetch = FetchType.EAGER,orphanRemoval=true)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="phase_pid",referencedColumnName="pid")
	private List<Filter> filters;

	public String getName() {
		return name;
	}

	public Phase setName(String name) {
		this.name = name;
		return this;
	}

	public boolean isEnable() {
		return enable;
	}

	public Phase setEnable(boolean enable) {
		this.enable = enable;
		return this;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public Phase setList(List<Filter> filters) {
		this.filters = filters;
		return this;
	}

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Review review;

	public Review getReview() {
		return review;
	}

	public Phase setReview(Review review) {
		this.review = review;
		return this;
	}

}
