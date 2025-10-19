package com.dacs.bff.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    
    private String username;
    
    private GenderDTO gender;                 
    
    private String bornDate;            
    
    private String registerDate;        
    
    private Boolean active;
    
    private String description;
}
