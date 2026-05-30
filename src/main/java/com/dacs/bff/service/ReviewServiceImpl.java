package com.dacs.bff.service;

import com.dacs.bff.dto.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl {

    @Autowired
    private ApiBackendService backendClient;    
    @Autowired
    private ApiConectorService conectorClient; 

    public List<AlbumReviewDTO> getTopReviewsForToday() {
        return backendClient.getTopReviewsForToday();
    }

    public AlbumReviewDTO createReview(ReviewCreateRequest request) {
    		AlbumDTO album = conectorClient.getAlbumById(String.valueOf(request.getAlbumId()));
    		
    		log.info("{}",album.getName());
    		
        UserDTO user = backendClient.getCurrentUser();

        ReviewCreateDTO reviewToPersist = new ReviewCreateDTO();
        reviewToPersist.setUserId(user.getId());
        reviewToPersist.setAlbumId(album.getId());
        reviewToPersist.setAlbum(album.getName());
        reviewToPersist.setCover(album.getImageUrl());
        reviewToPersist.setHighlight(request.getHighlight());
        reviewToPersist.setRating(request.getRating());
        reviewToPersist.setTags(request.getTags());
        reviewToPersist.setTone(request.getTone());
        reviewToPersist.setReviewBody(request.getReviewBody());

        return backendClient.createReview(reviewToPersist);
    }
}
