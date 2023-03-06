package com.example.frkv.jakartaeelabb.mapper;

import com.example.frkv.jakartaeelabb.dto.MovieDto;
import com.example.frkv.jakartaeelabb.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class Mapper {

    public List<MovieDto> map(List<Movie> all) {
        return all.stream().map(movie -> new MovieDto((movie.getId()), movie.getName())).toList();
    }

    public Movie map(MovieDto movie) {
        var m = new Movie();
        m.setId(movie.getId());
        m.setName(movie.getName());
        return m;
    }

    public MovieDto map(Movie movie) {
        return new MovieDto(movie);
    }
}
