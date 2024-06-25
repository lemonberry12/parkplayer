package com.movie.parkplayer.service;


import com.movie.parkplayer.repository.AdminMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMovieService {
    private final AdminMovieRepository adminMovieRepository;
}
