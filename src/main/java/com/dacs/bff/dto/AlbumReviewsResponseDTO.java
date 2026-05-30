package com.dacs.bff.dto;

import java.util.List;

import lombok.Data;

@Data
public class AlbumReviewsResponseDTO {

    private String albumId;
    private Double averageRating;
    private int totalReviews;
    private List<AlbumReviewDTO> reviews;
}
