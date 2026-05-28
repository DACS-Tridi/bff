package com.dacs.bff.dto;

import lombok.Data;
import java.util.Map;

@Data
public class SearchResultItemDTO {
    private String id;
    private String type;
    private String title;
    private String description;
    private Map<String, Object> metadata;
}
