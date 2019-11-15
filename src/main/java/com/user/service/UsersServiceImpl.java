package com.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.Data.AlbumsServiceClient;
import com.user.Data.UserEntity;
import com.user.Data.UserRepository;
import com.user.shared.UserDto;
import com.user.ui.model.AlbumResponseModel;
import com.user.util.GenericModelMapper;

@Service
public class UsersServiceImpl implements UserService {

	UserRepository userRepository;

	BCryptPasswordEncoder bCryptPasswordEncoder;

	RestTemplate restTemplate;

	AlbumsServiceClient albumsServiceClient;

	public UsersServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			AlbumsServiceClient albumsServiceClient) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.albumsServiceClient = albumsServiceClient;
	}

	public UserDto createUser(UserDto userDto) {

		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setEncrypedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		UserEntity userEntity = GenericModelMapper.modelConverter(userDto, UserEntity.class);
		userRepository.save(userEntity);
		return GenericModelMapper.modelConverter(userEntity, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(userEntity.getEmail(), userEntity.getEncrypedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		return GenericModelMapper.modelConverter(userEntity, UserDto.class);
	}

	@Override
	public UserDto findByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}

		UserDto userDto = GenericModelMapper.modelConverter(userEntity, UserDto.class);

		String albumUrl = String.format("https://ALBUMS-WS/users/%s/albums", userId);

		/*
		 * ResponseEntity<List<AlbumResponseModel>> responseEntity =
		 * restTemplate.exchange( "http://localhost:9999//users/4/albums",
		 * HttpMethod.GET, null, new
		 * ParameterizedTypeReference<List<AlbumResponseModel>>() { });
		 * 
		 * List<AlbumResponseModel> albumList = responseEntity.getBody();
		 */
		
		/*
		 * <!--
		 * https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-
		 * starter-openfeign --> <dependency>
		 * <groupId>org.springframework.cloud</groupId>
		 * <artifactId>spring-cloud-starter-openfeign</artifactId>
		 * <version>2.1.3.RELEASE</version> </dependency>
		 */
		
		//Usinging feign client to communicate with the other webservices 
		List<AlbumResponseModel> albumList = albumsServiceClient.getAlbums(userId);

		userDto.setAlbumList(albumList);

		return userDto;

	}

}
