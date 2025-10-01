package com.dacs.bff.service;

import java.util.List;

import com.dacs.bff.dto.AlbumDTO;

public interface ApiConectorService {
	
	public String ping();
	
	public List<AlbumDTO> albums();
	
//	public ItemDto getItemById(Integer id) throws Exception;
}
