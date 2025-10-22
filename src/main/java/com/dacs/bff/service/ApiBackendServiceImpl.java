package com.dacs.bff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dacs.bff.api.client.ApiBackendClient;
import com.dacs.bff.dto.AlbumReviewDTO;
import com.dacs.bff.dto.AlumnoDto;
import com.dacs.bff.dto.UserDTO;
import com.dacs.bff.dto.ReviewCreateRequest;
import com.dacs.bff.dto.ReviewCreateDTO;

@Service
public class ApiBackendServiceImpl implements ApiBackendService{

	@Autowired
	private ApiBackendClient apiBackendClient;
	
	@Override
	public String ping() {
		return apiBackendClient.ping();
	}

	@Override
	public AlumnoDto getAlumnoById(Long id) throws Exception {
		//TODO validar parametro y lanzar exepcion
		return apiBackendClient.alumnoById(id);
	}

	@Override
	public List<AlumnoDto> getAlumnos() {
		// TODO Auto-generated method stub
		return apiBackendClient.alumnos();
	}

	@Override
	public AlumnoDto savesAlumno(AlumnoDto alumno) throws Exception {
		//TODO validar parametro y lanzar exepcion
		return apiBackendClient.save(alumno);
	}

	@Override
	public AlumnoDto updateAlumno(AlumnoDto alumno) throws Exception {
		//TODO validar parametro y lanzar exepcion
		return apiBackendClient.update(alumno);
	}

	@Override
	public AlumnoDto deleteAlumno(Long id) throws Exception {
		//TODO validar parametro y lanzar exepcion
		return apiBackendClient.delete(id);
	}
	
    @Override
    public List<AlbumReviewDTO> getTopReviewsForToday() {
        return apiBackendClient.getTopReviewsForToday();
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

        // Keycloak adapter
        if (principal instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            var token = keycloakPrincipal.getKeycloakSecurityContext().getToken();
            UserDTO user = new UserDTO();
            user.setId(null);
            user.setUsername(token.getPreferredUsername());
            user.setDescription(token.getName());
            user.setActive(true);
            return user;
        }

        // OAuth2 Resource Server
        if (principal instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
            UserDTO user = new UserDTO();
            user.setUsername(jwt.getClaimAsString("preferred_username"));
            user.setDescription(jwt.getClaimAsString("name"));
            user.setActive(true);
            return user;
        }

        // String (sin auth)
        if (principal instanceof String username) {
            UserDTO user = new UserDTO();
            user.setId(0L);
            user.setUsername(username);
            user.setDescription("Anonymous User");
            user.setActive(true);
            return user;
        }

        throw new RuntimeException("Unsupported principal type: " + principal.getClass());
    }


}
