package edu.pavliuk.security25.movie;/*
    @author romat
    @project security25
    @class MovieRestController
    @version 1.0.0
    @since 09.03.2025 - 17.14
*/

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'UNKNOWN')")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Movie getMovie(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser() {
        return "Hello, User";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "Hello, Admin";
    }

    @GetMapping("/unknown")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String helloUnknown() {
        return "Hello, Unknown";
    }

    @GetMapping("/stranger")
    public String helloStranger() {
        return "Hello, Stranger";
    }
}
