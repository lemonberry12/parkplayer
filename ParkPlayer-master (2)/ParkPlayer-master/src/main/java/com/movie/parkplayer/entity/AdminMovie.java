package com.movie.parkplayer.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.File;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long movie_id;

    @Column(nullable = false)
    private String movie_name;

    @Column(nullable = false)
    private  String director;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = false)
    private String actor2;

    @Column(nullable = false)
    private File file;

    @Builder
    public AdminMovie(Long movie_id,String movie_name,String director,String actor,String actor2, File file){
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.director = director;
        this.actor = actor;
        this.actor2 = actor2;
        this.file = file;
    }

}
