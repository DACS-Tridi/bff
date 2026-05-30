package com.dacs.bff.dto;

import lombok.Data;

@Data
public class TridifyUserProfileDTO {
    private String id;
    private String username;
    private String displayName;
    private String roleTagline;
    private String avatarInitials;
    private String avatarGradient;
    private int streakDays;
}
