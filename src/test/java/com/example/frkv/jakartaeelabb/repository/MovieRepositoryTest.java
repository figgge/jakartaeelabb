package com.example.frkv.jakartaeelabb.repository;

import com.example.frkv.jakartaeelabb.entity.Movie;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieRepositoryTest {
    @InjectMocks
    MovieRepository repository;

    @Mock
    EntityManager entityManager;

    @Mock
    Query query;

    Movie movie1 = new Movie(1, "Movie 1");
    Movie movie2 = new Movie(2, "Movie 2");
    List<Movie> movies = List.of(movie1, movie2);

    @Test
    void findAllShouldReturnListWithMovies() {
// Arrange
        when(entityManager.createQuery("select m from Movie m")).thenReturn(query);
        when(query.getResultList()).thenReturn(movies);

        // Act
        List<Movie> result = repository.findAll();

        // Assert
        assertEquals(movies, result);

    }

    @Test
    void findOneShouldReturnMovie1() {
        when(entityManager.find(Movie.class, 1L)).thenReturn(movie1);

        var result = repository.findOne(1L);

        assertEquals(movie1, result.get());
    }

    @Test
    void insertMovieShouldAddMovieToMovies() {
        repository.insertMovie(movie1);

        assertEquals(movie1, movies.get(0));
    }

    @Test
    void deleteMovieShouldDeleteMovie() {
        when(entityManager.find(Movie.class, 1L)).thenReturn(movie1);

        repository.deleteMovie(1L);

        verify(entityManager).remove(movie1);
    }

    @Test
    void findAllByNameShouldReturnCorrectMovie() {
        when(entityManager.createQuery("select m from Movie m where m.name like :name")).thenReturn(query);
        when(query.getResultList()).thenReturn(movies);

        var result = repository.findAllByName("Movie 1");

        assertEquals(movies, result);
    }

    @Test
    void updateShouldUpdateNameOnMovie() {
        when(entityManager.find(Movie.class, 1L)).thenReturn(movie1);

        repository.update(1L, "Movie 11");

        assertEquals("Movie 11", movie1.getName());
    }
}
