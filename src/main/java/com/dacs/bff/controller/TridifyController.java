package com.dacs.bff.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dacs.bff.dto.AlbumDTO;
import com.dacs.bff.dto.AlbumReviewsResponseDTO;
import com.dacs.bff.dto.SearchResultItemDTO;
import com.dacs.bff.dto.TridifySearchPayloadDTO;
import com.dacs.bff.dto.TridifyUserProfileDTO;
import com.dacs.bff.dto.UserDTO;
import com.dacs.bff.service.ApiBackendService;
import com.dacs.bff.service.ApiConectorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tridify")
public class TridifyController {

    @Autowired
    private ApiConectorService apiConectorService;

    @Autowired
    private ApiBackendService apiBackendService;

    @PostMapping("/search")
    public List<SearchResultItemDTO> search(@RequestBody TridifySearchPayloadDTO payload) {
        log.info("Album search: {}", payload.getTerm());
        List<AlbumDTO> albums = apiConectorService.searchAlbums(payload.getTerm());
        return albums.stream().map(this::toSearchResultItem).collect(Collectors.toList());
    }

    @GetMapping("/albums/{spotifyId}")
    public ResponseEntity<AlbumDTO> getAlbumDetail(@PathVariable String spotifyId) {
        log.info("Album detail: {}", spotifyId);
        AlbumDTO album = apiConectorService.getAlbumById(spotifyId);
        return ResponseEntity.ok(album);
    }

    @GetMapping("/reviews/album/{albumId}")
    public ResponseEntity<AlbumReviewsResponseDTO> getReviewsByAlbum(@PathVariable String albumId) {
        log.info("Reviews for album: {}", albumId);
        AlbumReviewsResponseDTO response = apiBackendService.getReviewsByAlbumId(albumId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user-profile")
    public ResponseEntity<TridifyUserProfileDTO> getUserProfile() {
        UserDTO user = apiBackendService.getCurrentUser();

        String username = user.getUsername() != null ? user.getUsername() : "usuario";
        String initials = username.substring(0, 1).toUpperCase();

        TridifyUserProfileDTO profile = new TridifyUserProfileDTO();
        profile.setId(user.getId() != null ? user.getId().toString() : "0");
        profile.setUsername(username);
        profile.setDisplayName(username);
        profile.setRoleTagline("Music Reviewer");
        profile.setAvatarInitials(initials);
        profile.setAvatarGradient("linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%)");
        profile.setStreakDays(0);

        return ResponseEntity.ok(profile);
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
