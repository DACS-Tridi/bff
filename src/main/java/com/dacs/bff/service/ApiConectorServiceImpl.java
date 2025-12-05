package com.dacs.bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacs.bff.api.client.ApiConectorClient;
import com.dacs.bff.controller.HomeController;
import com.dacs.bff.dto.AlbumDTO;
import com.dacs.bff.exeption.BffException;
import com.dacs.bff.exeption.ConectorException;
import com.dacs.bff.exeption.ErrorEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiConectorServiceImpl implements ApiConectorService {

	@Autowired
	private ApiConectorClient apiConectorClient;
	
	@Override
	public String ping() {
		return apiConectorClient.ping();
	}

	@Override
	public List<AlbumDTO> albums() {
		return apiConectorClient.albums();
	}
	
	@Override
	public List<AlbumDTO> searchAlbums(String query) {
		return apiConectorClient.searchAlbums(query);
	}
	
	@Override
	public AlbumDTO getAlbumById(String id) {
		log.info("🎧 getAlbumById() llamado con id = [{}]", id);
		if(id == null) {
			throw new BffException(ErrorEnum.DATO_VALOR_INCORRECTO,"Album id no puede ser nulo");
		}
		return apiConectorClient.getAlbumById(id);
	}

//	@Override
//	public ItemDto getItemById(Integer id) throws Exception {
//		
//		if(id == null) {
//			throw new BffException(ErrorEnum.DATO_VALOR_INCORRECTO,"Item id no puede ser nulo");
//		}
//		
//		List<ItemDto> items = apiConectorClient.items();
//		ItemDto item = null;
//		for (ItemDto itemDto : items) {
//			if(itemDto.getId() == id) {
//				item = itemDto;
//				break;
//			}
//			
//		}
//		if(item == null) {
//			throw new ConectorException(ErrorEnum.DATO_SIN_VALOR_INGRESADO,"Item no encontrado");
//		}	
//
//		return item;
//	}

}
