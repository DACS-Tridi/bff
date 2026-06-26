package com.dacs.bff.controller;

import com.dacs.bff.dto.AlbumReviewDTO;
import com.dacs.bff.dto.ReviewCreateRequest;
import com.dacs.bff.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tridify/reviews")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @GetMapping
    public ResponseEntity<List<AlbumReviewDTO>> getAllReviews() {
        List<AlbumReviewDTO> reviews = reviewServiceImpl.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/today")
    public ResponseEntity<List<AlbumReviewDTO>> getTopReviewsForToday() {
        List<AlbumReviewDTO> reviews = reviewServiceImpl.getTopReviewsForToday();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AlbumReviewDTO>> getMyReviews() {
        List<AlbumReviewDTO> reviews = reviewServiceImpl.getMyReviews();
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<AlbumReviewDTO> createReview(@RequestBody ReviewCreateRequest request) {
        AlbumReviewDTO createdReview = reviewServiceImpl.createReview(request);
        return ResponseEntity.ok(createdReview);
    }
}
