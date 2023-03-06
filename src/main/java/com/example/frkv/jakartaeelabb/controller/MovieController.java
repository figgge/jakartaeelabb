package com.example.frkv.jakartaeelabb.controller;

import com.example.frkv.jakartaeelabb.dto.MovieDto;
import com.example.frkv.jakartaeelabb.entity.Movie;
import com.example.frkv.jakartaeelabb.exception.IdNotFoundException;
import com.example.frkv.jakartaeelabb.mapper.Mapper;
import com.example.frkv.jakartaeelabb.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/movies")
public class MovieController {

    @Inject
    MovieRepository repository;

    @Inject
    Mapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getAllMovies(@QueryParam("name") String name) {
        if (name == null)
            return mapper.map(repository.findAll());
        return mapper.map((repository.findAllByName(name)));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneMovie(@PathParam("id") Long id) {
        var movie = repository.findOne(id);
        if (movie.isPresent())
            return Response.ok().entity(movie.get()).build();
        throw new IdNotFoundException("Id not found: " + id);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMovie(@PathParam("id") Long id) {
        repository.deleteMovie(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(@Valid Movie movie) {
        repository.insertMovie(movie);
        return Response.created(URI.create("movies/" + movie.getId())).build();
    }

    @PUT
    public void updateMovie(@QueryParam("id") Long id, @QueryParam("name") String name) {
        repository.update(id, name);
    }

}
