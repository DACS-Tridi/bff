package com.dacs.bff.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dacs.bff.dto.AlbumDTO;
import com.dacs.bff.dto.SearchResultItemDTO;
import com.dacs.bff.dto.TridifySearchPayloadDTO;
import com.dacs.bff.service.ApiConectorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tridify")
public class TridifyController {

    @Autowired
    private ApiConectorService apiConectorService;

    @PostMapping("/search")
    public List<SearchResultItemDTO> search(@RequestBody TridifySearchPayloadDTO payload) {
        log.info("Album search: {}", payload.getTerm());
        List<AlbumDTO> albums = apiConectorService.searchAlbums(payload.getTerm());
        return albums.stream().map(this::toSearchResultItem).collect(Collectors.toList());
    }

    private SearchResultItemDTO toSearchResultItem(AlbumDTO album) {
        SearchResultItemDTO item = new SearchResultItemDTO();
        item.setId(album.getId());
        item.setType("album");
        item.setTitle(album.getName());

        String artists = album.getArtists() != null ? String.join(", ", album.getArtists()) : "";
        String year = album.getReleaseDate() != null && album.getReleaseDate().length() >= 4
                ? album.getReleaseDate().substring(0, 4)
                : (album.getReleaseDate() != null ? album.getReleaseDate() : "");
        item.setDescription(artists.isEmpty() ? year : (year.isEmpty() ? artists : artists + " · " + year));

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("spotifyId", album.getId());
        metadata.put("imageUrl", album.getImageUrl());
        metadata.put("totalTracks", album.getTotalTracks());
        item.setMetadata(metadata);

        return item;
    }
}
