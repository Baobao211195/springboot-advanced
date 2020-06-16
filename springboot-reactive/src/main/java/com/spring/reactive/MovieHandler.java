package com.spring.reactive;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class MovieHandler {
    public Mono<ServerResponse> listMovies(ServerRequest request) {
        return request.bodyToMono(ServerResponse.class);
    }
    public Mono<ServerResponse> createMovie(ServerRequest request) {
        return request.bodyToMono(ServerResponse.class);
    }
    public Mono<ServerResponse> getMovie(ServerRequest request) {
        return request.bodyToMono(ServerResponse.class);
    }

}
