package com.dacs.bff.service;

import java.util.List;

import com.dacs.bff.dto.AlbumReviewDTO;
import com.dacs.bff.dto.AlumnoDto;
import com.dacs.bff.dto.UserDTO;
import com.dacs.bff.dto.ReviewCreateDTO;
import com.dacs.bff.dto.ReviewCreateRequest;

public interface ApiBackendService {

	public String ping();

	public AlumnoDto getAlumnoById(Long id) throws Exception;

	public List<AlumnoDto> getAlumnos();

	public AlumnoDto savesAlumno(AlumnoDto alumno) throws Exception;

	public AlumnoDto updateAlumno(AlumnoDto alumno) throws Exception;

	public AlumnoDto deleteAlumno(Long id) throws Exception;
	
    public List<AlbumReviewDTO> getTopReviewsForToday();

    public AlbumReviewDTO createReview(ReviewCreateDTO review);
    
    public UserDTO getUserById(Long id);
    
    public List<UserDTO> getUsers();
    
    public UserDTO saveUser(UserDTO user);
    
    public UserDTO updateUser(UserDTO user);
    
    public void deleteUser(Long id);
    
    public UserDTO getCurrentUser();
}
