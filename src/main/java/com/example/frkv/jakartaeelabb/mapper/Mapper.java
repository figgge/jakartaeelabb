package com.example.frkv.jakartaeelabb.mapper;

import com.example.frkv.jakartaeelabb.dto.MovieDto;
import com.example.frkv.jakartaeelabb.entity.Movie;
import com.example.frkv.jakartaeelabb.repository.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class Mapper {

    public List<MovieDto> map(List<Movie> all) {
        return all.stream().map(movie -> new MovieDto((movie.getId()), movie.getName())).toList();
    }
}
