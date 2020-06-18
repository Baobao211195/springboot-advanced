package com.spring.reactive;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.reactive.dto.Movie;
import com.spring.reactive.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MovieHandler {

    private final MovieRepository movieRepository;

    public Mono<ServerResponse> listMovies(ServerRequest request) {
        Flux<Movie> movies = movieRepository.listMovies();
        return
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movies, Movie.class);
    }

    public Mono<ServerResponse> getMovieById(ServerRequest request) {
        Long id = request.pathVariable("id")
            .map(p -> (Long) p)
            .orElseThrow(() -> new IllegalArgumentException("error"));
        Mono<Movie> movie = movieRepository.getById(id);
        return
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movie, Movie.class);
    }
    public Mono<ServerResponse> saveMovie(ServerRequest request) {
        return request
            .bodyToMono(Movie.class)
            .map(p -> movieRepository.saveMovie(p))
            .flatMap(el ->
                    ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(el, Movie.class));
    }
    public Mono<ServerResponse> getMovie(ServerRequest request) {
        return request.bodyToMono(ServerResponse.class);
    }

}
