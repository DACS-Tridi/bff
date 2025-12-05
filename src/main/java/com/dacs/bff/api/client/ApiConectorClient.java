package com.dacs.bff.api.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dacs.bff.dto.AlbumDTO;

@FeignClient(
		name = "apiConectorClient", 
		url = "${feign.client.config.apiconectorclient.url}"
		)
public interface ApiConectorClient {

	   @GetMapping("/ping")
	   String ping();
	   
	   @GetMapping("/spotify/albums")
	   List<AlbumDTO> albums();
	   
	   @GetMapping("/spotify/albums/search")
	   List<AlbumDTO> searchAlbums(@RequestParam("q") String query);
	   
	   @GetMapping("/spotify/album/")
	   AlbumDTO getAlbumById(@RequestParam("id") String id);
}
