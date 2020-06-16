# Springboot-advanced
From microservice pattern 

## springboot-reactive

### spring WebFlux
Trong mô hình lập trình hướng function của webFlux có 2 thành phần cơ bản.
+ HandlerFunction : để xử lý các request(tương đương với @Controller)
+ RouterFunction : Để điều hướng (tương đương vs @RequestMapping)

#### HandlerFunction.

Nhận đầu vào là một object ServerRequest và trả về một Mono<ServerResponse>. Cả 2 object ServerRequest 
và ServerResponse để là immutable và reactive, build trên top của Reactor.
+ ServerRequest có thể chuyển đổi body thành cả Mono và Flux bằng cách sử dụng abstract class BodyExtractor để 
thực hiện hoặc ta cũng có thể thực hiện bằng nhiều cách khác.

```java
Mono<String> hello = request.body(BodyExtractors.toMono(String.class ));
Mono<String> hello1 = request.bodyToMono(String.class);

Flux<String> person = request.body(BodyExtractors.toFlux(Person.class ));
Flux<String> person1 = request.bodyToFlux(Person.class);
```
+ ServerResponse dùng để chuyển tạo ra response trả về phía client.
```java
HandlerFunction>ServerResponse> handler = request -> ServerResponse.ok().body(fromObject("Sample Handler fuunction"))
```
Lời khuyên là chúng ta group lại thành 1 HandlerFunction object trong 1 class có nhiều method và định nghĩa cụ thể 
mỗi function như dưới đây.
```java
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;
public class MovieHandler {
    public Mono<String> listMovies(ServerRequest request) {
        // do stuff
        return  request.bodyToMono(String.class);
    }
    public Mono<ServerResponse> getMovie(ServerRequest request) {
        // Logic that returns one Movie object
    }
}
```
#### RouteFunctions.

Các request tới phải qua một bộ interceptor được gọi là RouterFunction và dựa theo việc cấu hình route nó điều hướng tới
đúng HandlerFunction. Nếu có 1 route match vs request RouterFunction đưa vào ServerRequest và trả về một Mono<HandlerFunction>
nếu không sẽ return về một Mono.empty().
Để tạo ra một RouteFunctions ta làm như sau:
```java
RouteFunctions.route(RequestPredicate, HandlerFunction);
```
***RequestPredicate*** là một class để định nghĩa việc matching vs pattern của request.

```java
RouterFunction<ServerResponse> routeFunctionSample =
   RouterFunctions.route(RequestPredicates.path("/sample-route"), request -> Response.ok().body(fromObject("Sample Route")));
```
Chúng ta có thể định nghĩa nhiều RouterFunction object để tổ hợp cùng nhau hoặc kết hợp chúng
```java
RouterFunction.and(RouterFunction)
RouterFunction.andRoute(RequestPredicate, HandlerFunction)
```
Example :
```java
RouterFunction<ServerResponse> movieRoutes = route(GET("/movie/{id}").and(accept(APPLICATION_JSON)),
    handler::getMovie).andRoute(GET("/movie").and(accept(APPLICATION_JSON)),
    handler::listMovies).andRoute(POST("/movie").and(contentType(APPLICATION_JSON)),
    handler::createMovie);
```
#### Spring WebFlux server support