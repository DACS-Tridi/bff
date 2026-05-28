package com.dacs.bff.dto;

import lombok.Data;
import java.util.List;

@Data
public class ReviewCreateRequest {
    private String albumId;
    private String highlight;
    private Double rating;
    private List<String> tags;
    private String tone;
    private String reviewBody;
}
