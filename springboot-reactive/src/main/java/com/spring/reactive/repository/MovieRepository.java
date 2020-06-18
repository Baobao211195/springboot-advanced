package com.spring.reactive.repository;

import com.spring.reactive.dto.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieRepository{
    Mono<Movie> getById(Long id);
    Flux<Movie> listMovies();
    Mono<Movie> saveMovie(Movie movie);
}
