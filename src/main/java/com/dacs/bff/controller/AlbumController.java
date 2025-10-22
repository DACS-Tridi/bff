package com.dacs.bff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dacs.bff.dto.AlbumDTO;
import com.dacs.bff.service.ApiConectorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/spotify")
public class AlbumController {

	@Autowired
	private ApiConectorService apiConectorService;

	@GetMapping(value = "/albums")
    public List<AlbumDTO> albums() {
		log.info("Ingrese a homecontroller conector ping");
		try {
			return apiConectorService.albums();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "/albums/search")
    public List<AlbumDTO> searchAlbums(@RequestParam("q") String query) {
		log.info("Busqueda de album: {}", query);
		try {
			return apiConectorService.searchAlbums(query);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
	
