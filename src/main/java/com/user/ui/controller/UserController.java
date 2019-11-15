package com.user.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.UserService;
import com.user.shared.UserDto;
import com.user.ui.model.CreateUserRequestModel;
import com.user.ui.model.CreateUserResponceModel;
import com.user.util.GenericModelMapper;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("testing")
	public String check() {
		return "User Controller";
	}

	@PostMapping
	public ResponseEntity<CreateUserResponceModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {

		UserDto userDto = userService.createUser(GenericModelMapper.modelConverter(userDetails, UserDto.class));

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(GenericModelMapper.modelConverter(userDto, CreateUserResponceModel.class));
	}

	 @GetMapping(value = "/{userId}", 
	            produces = { 
	                MediaType.APPLICATION_JSON_VALUE,
	                MediaType.APPLICATION_XML_VALUE,
	            })
	 public ResponseEntity<CreateUserResponceModel> getUserById(@PathVariable String userId) {

		UserDto userDto = userService.findByUserId(userId);

		System.out.println("========" + userDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(GenericModelMapper.modelConverter(userDto, CreateUserResponceModel.class));
	}

}
