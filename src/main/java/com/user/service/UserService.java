package com.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.user.Data.UserEntity;
import com.user.shared.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto userDto);

	UserDto getUserDetailsByEmail(String email);
	
	UserDto findByUserId(String userId);

}
