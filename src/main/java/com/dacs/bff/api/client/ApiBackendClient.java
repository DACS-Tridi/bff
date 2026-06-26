package com.dacs.bff.api.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.dacs.bff.dto.AlbumReviewDTO;
import com.dacs.bff.dto.AlbumReviewsResponseDTO;
import com.dacs.bff.dto.AlumnoDto;
import com.dacs.bff.dto.UserDTO;
import com.dacs.bff.dto.BuildInfoDTO;
import com.dacs.bff.dto.ReviewCreateDTO;


@FeignClient(
			name = "apiBackendClient", 
			url = "${feign.client.config.apiBackendClient.url}"
			)

public interface ApiBackendClient {

    @GetMapping("/ping")
    String ping();
    
    @GetMapping("/version")
    BuildInfoDTO version();
    
    @GetMapping("/alumno")
    List<AlumnoDto> alumnos();
    
    @GetMapping("/alumno/{id}")
    AlumnoDto alumnoById(@PathVariable("id") Long id);
    
    @PostMapping("/alumno")
    AlumnoDto save(@RequestBody AlumnoDto alumno);
    
    @PutMapping("/alumno")
    AlumnoDto update(@RequestBody AlumnoDto alumno);
    
    @DeleteMapping("/alumno/{id}")
    AlumnoDto delete(@PathVariable("id") Long id);
    
    @GetMapping("/reviews")
    List<AlbumReviewDTO> getAllReviews();

    @GetMapping("/reviews/today")
    List<AlbumReviewDTO> getTopReviewsForToday();

    @GetMapping("/reviews/user/{userId}")
    List<AlbumReviewDTO> getReviewsByUserId(@PathVariable Long userId);

    @GetMapping("/reviews/album/{albumId}")
    AlbumReviewsResponseDTO getReviewsByAlbumId(@PathVariable("albumId") String albumId);

    @PostMapping("/reviews")
    AlbumReviewDTO createReview(@RequestBody ReviewCreateDTO review);
    
    @GetMapping("/user/by-username")
    UserDTO getUserByUsername(@RequestParam("username") String username);

    @GetMapping("/user")
    List<UserDTO> getUsers();

    @GetMapping("/user/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @PostMapping("/user")
    UserDTO saveUser(@RequestBody UserDTO user);

    @PutMapping("/user")
    UserDTO updateUser(@RequestBody UserDTO user);

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
