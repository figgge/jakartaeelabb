package com.example.frkv.jakartaeelabb.dto;

import com.example.frkv.jakartaeelabb.entity.Movie;

public class MovieDto {
    private Long id;
    private String name;

    public MovieDto() {}

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
    }

    public MovieDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
