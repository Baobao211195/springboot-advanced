package com.spring.reactive;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RouteFunctionApplication {
    private final MovieHandler handler;

    public RouterFunction<ServerResponse> movie() {
        RouterFunction<ServerResponse> movieRoutes =
            route(RequestPredicates.GET("/movie/{id}").and(accept(APPLICATION_JSON)),
                handler::getMovie).andRoute(RequestPredicates.GET("/movie").and(accept(APPLICATION_JSON)),
                handler::listMovies).andRoute(RequestPredicates.POST("/movie").and(contentType(APPLICATION_JSON)),
                handler::createMovie);
        return movieRoutes;
    }


}
