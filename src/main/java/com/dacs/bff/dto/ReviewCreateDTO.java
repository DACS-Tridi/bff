package com.dacs.bff.dto;

import lombok.Data;
import java.util.List;

@Data
public class ReviewCreateDTO {
    private Long userId;
    private String albumId;
    private String albumTitle;
    private String cover;
    private String highlight;
    private Double rating;
    private List<String> tags;
    private String tone;
    private String reviewBody;
}
