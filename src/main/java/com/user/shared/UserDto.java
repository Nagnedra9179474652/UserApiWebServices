package com.user.shared;

import com.user.ui.model.AlbumResponseModel;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 7654330791323532049L;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
	private String encrypedPassword;
	private List<AlbumResponseModel> albumList;

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

	public List<AlbumResponseModel> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<AlbumResponseModel> albumList) {
		this.albumList = albumList;
	}

}
