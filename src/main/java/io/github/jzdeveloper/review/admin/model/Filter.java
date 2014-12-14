package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author mazhyb
 *
 */

@Entity
@Table(name = "filter")
public class Filter extends AbstractModel<Filter> {

	private static final long serialVersionUID = -9209367623054790760L;

	private String name;

	private String value;

	private boolean enable;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Phase phase;

	public String getName() {
		return name;
	}

	public Filter setName(String name) {
		this.name = name;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Filter setValue(String value) {
		this.value = value;
		return this;
	}

	public boolean isEnable() {
		return enable;
	}

	public Filter setEnable(boolean enable) {
		this.enable = enable;
		return this;
	}

	public Phase getPhase() {
		return phase;
	}

	public Filter setPhase(Phase phase) {
		this.phase = phase;
		return this;
	}

}
