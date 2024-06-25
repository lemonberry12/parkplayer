package com.movie.parkplayer.dto;


import com.movie.parkplayer.entity.AdminMovie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminMovieDto {
    private Long movieId;
    private String movieName;
    private String director;
    private String actor1;
    private String actor2;
    private MultipartFile file;


    public AdminMovie toAdminMovie() {
        return AdminMovie.builder()
                .movie_id(movieId)
                .movie_name(movieName)
                .director(director)
                .actor(actor1)
                .actor2(actor2)
                .build();
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }
}
