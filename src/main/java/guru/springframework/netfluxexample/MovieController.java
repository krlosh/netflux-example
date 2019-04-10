package guru.springframework.netfluxexample;

import guru.springframework.netfluxexample.domain.Movie;
import guru.springframework.netfluxexample.domain.MovieEvent;
import guru.springframework.netfluxexample.services.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id){
        return this.movieService.events(id);
    }

    @GetMapping(value = "/{id}")
    Mono<Movie> getMovieById(@PathVariable String id){
        return this.movieService.getMovieById(id);
    }

    @GetMapping
    Flux<Movie> getAllMovies(){
        return this.movieService.getAllMovies();
    }
}
