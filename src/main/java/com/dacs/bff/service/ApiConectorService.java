package com.dacs.bff.service;

import java.util.List;

import com.dacs.bff.dto.AlbumDTO;

public interface ApiConectorService {
	
	public String ping();
	
	public List<AlbumDTO> albums();
	
	public List<AlbumDTO> searchAlbums(String query);
	
//	public ItemDto getItemById(Integer id) throws Exception;
}
