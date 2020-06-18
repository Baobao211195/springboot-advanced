package com.spring.reactive.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.reactive.MovieHandler;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction1(MovieHandler movieHandler) {
        return
            route(GET("/").and(accept(MediaType.APPLICATION_JSON)), movieHandler::listMovies)
            .andRoute(GET("/api/movie").and(accept(MediaType.APPLICATION_JSON)), movieHandler::listMovies)
            .andRoute(GET("/api/movie/{id}").and(accept(MediaType.APPLICATION_JSON)), movieHandler::getMovieById)
            .andRoute(POST("/api/movie").and(accept(MediaType.APPLICATION_JSON)), movieHandler::saveMovie);
//                .andRoute(PUT("/api/movie/{id}").and(accept(MediaType.APPLICATION_JSON)), movieHandler::putMovie)
//                .andRoute(DELETE("/api/movie/{id}") .and(accept(MediaType.APPLICATION_JSON)), movieHandler::deleteMovie);
    }
}
