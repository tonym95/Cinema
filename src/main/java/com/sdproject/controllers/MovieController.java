package com.sdproject.controllers;

import com.sdproject.entities.Cinema;
import com.sdproject.entities.Movie;
import com.sdproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movie/")
    public Collection<Movie> movies() {
        return movieRepository.findAll();
    }

    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable("id") Integer movieId) {
        Movie movie = movieRepository.findOne(movieId);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movie);
    }

    @DeleteMapping(value = "/movie/delete/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") Integer movieId) {
        Movie movie = movieRepository.findOne(movieId);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        movieRepository.delete(movie);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/movie/")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping(value = "/movie/{id}")
    public ResponseEntity<Movie> updateCinema(@PathVariable("id") Integer movieId, @Valid @RequestBody Movie movie) {
        Movie movieExist = movieRepository.findOne(movieId);

        if (movieExist == null) {
            return ResponseEntity.notFound().build();
        }

        movieExist.setName(movie.getName());
        movieExist.setYear(movie.getYear());
        movieExist.setGenre(movie.getGenre());
        movieExist.setCinemas(movie.getCinemas());
        movieExist.setReservations(movie.getReservations());

        movieExist.computeRating(movie.getRating());

        Movie updatedMovie = movieRepository.save(movieExist);

        return ResponseEntity.ok(updatedMovie);
    }

}
