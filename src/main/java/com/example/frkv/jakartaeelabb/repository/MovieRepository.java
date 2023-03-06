package com.example.frkv.jakartaeelabb.repository;


import com.example.frkv.jakartaeelabb.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class MovieRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Movie> findAll(){
        var query = entityManager.createQuery("select m from Movie m");
        return (List<Movie>) query.getResultList();
    }

    public Optional<Movie> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    public void insertMovie(Movie movie) {
        entityManager.persist(movie);
    }

    public void deleteMovie(Long id) {
        var movie = findOne(id);
        movie.ifPresent((m -> entityManager.remove(m)));
    }

    public List<Movie> findAllByName(String name) {
        var query = entityManager.createQuery("select m from Movie m where m.name like :name");

        query.setParameter("name", name);
        return (List<Movie>) query.getResultList();
    }

    public void update(Long id, String name) {
        var movie = findOne(id);
        movie.ifPresent((m) -> m.setName(name));
    }
}
