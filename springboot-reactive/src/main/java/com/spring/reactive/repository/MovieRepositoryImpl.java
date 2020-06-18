package com.spring.reactive.repository;

import org.springframework.stereotype.Repository;

import com.spring.reactive.dto.Movie;
import java.util.*;
import javax.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private Map<Long, Movie> movies = new HashMap<Long, Movie>();

    @PostConstruct
    public void initIt() throws Exception {
        movies.put(Long.valueOf(1), new Movie(Long.valueOf(1), "Moonlight", "Drama"));
        movies.put(Long.valueOf(2), new Movie(Long.valueOf(2), "Dunkirk", "Drama/Thriller"));
        movies.put(Long.valueOf(3), new Movie(Long.valueOf(3), "Get Out","Mystery/Thriller"));
        movies.put(Long.valueOf(4), new Movie(Long.valueOf(4), "The Shape of Water", "Drama/Thriller"));
    }

    @Override
    public Mono<Movie> getById(Long id) {
        return Mono.just(movies.get(id));
    }

    @Override
    public Flux<Movie> listMovies() {
        return Flux.fromIterable(movies.values());
    }

    @Override
    public Mono<Movie> saveMovie(Movie movie) {
        return Mono.just(movies.put(Long.valueOf(5), movie));
    }
}
