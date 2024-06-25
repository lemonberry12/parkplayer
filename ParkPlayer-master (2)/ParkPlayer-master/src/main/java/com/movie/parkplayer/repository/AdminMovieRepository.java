package com.movie.parkplayer.repository;

import com.movie.parkplayer.entity.AdminMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMovieRepository extends JpaRepository<AdminMovie, Long> {
}
