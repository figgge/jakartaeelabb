package com.example.frkv.jakartaeelabb.validate;

import com.example.frkv.jakartaeelabb.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieValidator {

    public boolean validate(Movie movie) {
        return movie.getName() != null && !movie.getName().isEmpty();
    }
}
