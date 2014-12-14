package io.github.jzdeveloper.review.admin.model;

import io.github.jzdeveloper.review.admin.base.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends AbstractModel<User>{

	private static final long serialVersionUID = 1195322494272900437L;

	private String name;
	
	private String password;
	
	private String email;
	
	private String nickname;


	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public User setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	
	
	
}
