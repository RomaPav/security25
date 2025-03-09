package edu.pavliuk.security25.movie;/*
    @author romat
    @project security25
    @class MovieRestController
    @version 1.0.0
    @since 09.03.2025 - 17.14
*/

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping("/")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/user")
    public String helloUser() {
        return "Hello, User";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello, Admin";
    }

    @GetMapping("/unknown")
    public String helloUnknown() {
        return "Hello, Unknown";
    }

}
