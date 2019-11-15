package com.user.Data;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.ui.model.AlbumResponseModel;

@FeignClient(name = "album-ws")
public interface AlbumsServiceClient {

	@GetMapping(value = "/user/{id}/albums")
	public List<AlbumResponseModel> getAlbums(@PathVariable String id);
}
