package com.user.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5472478252879850250L;
    
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false,length = 120)
	private String firstName;
	@Column(nullable = false,length = 120)
	private String lastName;
	@Column(nullable = false,length = 150,unique = true)
	private String email;
	@Column(nullable = false,length = 150)
	private String password;
	@Column(nullable = false,unique = true)
	private String userId;
	@Column(nullable = false,length = 150)
	private String encrypedPassword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncrypedPassword() {
		return encrypedPassword;
	}

	public void setEncrypedPassword(String encrypedPassword) {
		this.encrypedPassword = encrypedPassword;
	}

}
