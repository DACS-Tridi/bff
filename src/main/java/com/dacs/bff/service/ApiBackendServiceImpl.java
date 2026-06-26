package com.dacs.bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dacs.bff.api.client.ApiBackendClient;
import com.dacs.bff.dto.AlbumReviewDTO;
import com.dacs.bff.dto.AlbumReviewsResponseDTO;
import com.dacs.bff.dto.AlumnoDto;
import com.dacs.bff.dto.UserDTO;
import com.dacs.bff.dto.ReviewCreateDTO;

@Service
public class ApiBackendServiceImpl implements ApiBackendService {

	@Autowired
	private ApiBackendClient apiBackendClient;

	@Override
	public String ping() {
		return apiBackendClient.ping();
	}

	@Override
	public AlumnoDto getAlumnoById(Long id) throws Exception {
		return apiBackendClient.alumnoById(id);
	}

	@Override
	public List<AlumnoDto> getAlumnos() {
		return apiBackendClient.alumnos();
	}

	@Override
	public AlumnoDto savesAlumno(AlumnoDto alumno) throws Exception {
		return apiBackendClient.save(alumno);
	}

	@Override
	public AlumnoDto updateAlumno(AlumnoDto alumno) throws Exception {
		return apiBackendClient.update(alumno);
	}

	@Override
	public AlumnoDto deleteAlumno(Long id) throws Exception {
		return apiBackendClient.delete(id);
	}

	@Override
	public List<AlbumReviewDTO> getAllReviews() {
		return apiBackendClient.getAllReviews();
	}

	@Override
	public List<AlbumReviewDTO> getTopReviewsForToday() {
		return apiBackendClient.getTopReviewsForToday();
	}

	@Override
	public List<AlbumReviewDTO> getReviewsByUserId(Long userId) {
		return apiBackendClient.getReviewsByUserId(userId);
	}

	@Override
	public AlbumReviewsResponseDTO getReviewsByAlbumId(String albumId) {
		return apiBackendClient.getReviewsByAlbumId(albumId);
	}

	@Override
	public AlbumReviewDTO createReview(ReviewCreateDTO review) {
		return apiBackendClient.createReview(review);
	}

	@Override
	public UserDTO getUserById(Long id) {
		return apiBackendClient.getUserById(id);
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		return apiBackendClient.getUserByUsername(username);
	}

	@Override
	public List<UserDTO> getUsers() {
		return apiBackendClient.getUsers();
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		return apiBackendClient.saveUser(user);
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		return apiBackendClient.updateUser(user);
	}

	@Override
	public void deleteUser(Long id) {
		apiBackendClient.deleteUser(id);
	}

	@Override
	public UserDTO getCurrentUser() {
		var authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			throw new RuntimeException("No authenticated user found");
		}

		var principal = authentication.getPrincipal();
		String username = null;

		if (principal instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
			username = jwt.getClaimAsString("preferred_username");
		} else if (principal instanceof String str) {
			username = str;
		}

		if (username == null) {
			throw new RuntimeException("Could not resolve username from principal: " + principal.getClass());
		}

		// Busca o crea el usuario en el backend para obtener su id real
		return apiBackendClient.getUserByUsername(username);
	}
}
