package com.dacs.bff.dto;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;

@Data
public class AlbumReviewDTO {
    private Long id;
    
    private String user;
    
    private Long userId;
    
    private String album;
    
    private String albumId;
    
    private String highlight;
    
    private String cover;
    
    private Double rating;
    
    private Stats stats;
    
    private List<String> tags;
    
    private String tone;
    
    private ZonedDateTime postedAt;

    @Data
    public static class Stats {
        private int likes;
        
        private int comments;
        
        private int shares;
    }
}
